package com.librarymanagement.maids.serviceImplementation;

import com.librarymanagement.maids.domain.Patron;
import com.librarymanagement.maids.domain.RequestModel.PatronRequest;
import com.librarymanagement.maids.dto.PatronDTO;
import com.librarymanagement.maids.exception.ResourceNotFoundException;
import com.librarymanagement.maids.repository.PatronRepository;
import com.librarymanagement.maids.service.PatronService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class PatronServiceImpl implements PatronService {

    private final PatronRepository patronRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PatronServiceImpl(PatronRepository patronRepository, ModelMapper modelMapper) {
        this.patronRepository = patronRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Cacheable("patrons")
    @Transactional(readOnly = true)
    public List<PatronDTO> getAllPatrons() {
        log.debug("Fetching all patrons...");
        List<Patron> patrons = patronRepository.findAll();
        List<PatronDTO> patronDTOs = patrons.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        log.debug("Fetched {} patrons", patronDTOs.size());
        return patronDTOs;
    }
    @Override
    @Cacheable(value = "patrons", key = "#id")
    @Transactional(readOnly = true)
    public PatronDTO getPatronById(Long id) {
        log.debug("Fetching patron by ID: {}", id);
        Patron patron = patronRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Patron not found with id: {}", id);
                    return new ResourceNotFoundException("Patron not found with id: " + id);
                });
        log.debug("Fetched patron: {}", patron);
        return convertToDTO(patron);
    }
    public Patron getPatronByIds(Long id) {
        return patronRepository.findById(id).orElse(null);
    }
    @Override
    @Transactional
    public PatronDTO createPatron(PatronRequest patronRequest) {
        log.debug("Creating new patron: {}", patronRequest);
        Patron patron = convertToEntity(patronRequest);
        patron = patronRepository.save(patron);
        log.debug("Created patron: {}", patron);
        return convertToDTO(patron);
    }

    @Override
    @Transactional
    @CacheEvict(value = "patrons", allEntries = true)
    public PatronDTO updatePatron(Long id, PatronRequest patronRequest) {
        log.debug("Updating patron with ID: {}", id);
        Patron existingPatron = patronRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Patron not found with id: {}", id);
                    return new ResourceNotFoundException("Patron not found with id: " + id);
                });
        existingPatron.setName(patronRequest.getName());
        patronRepository.save(existingPatron);
        log.debug("Updated patron: {}", existingPatron);
        return convertToDTO(existingPatron);
    }

    @Override
    @Transactional
    @CacheEvict(value = "patrons", key = "#id")
    public void deletePatron(Long id) {
        log.debug("Deleting patron with ID: {}", id);
        Patron patron = patronRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Patron not found with id: {}", id);
                    return new ResourceNotFoundException("Patron not found with id: " + id);
                });
        patronRepository.delete(patron);
        log.debug("Deleted patron with ID: {}", id);
    }

    private PatronDTO convertToDTO(Patron patron) {
        return modelMapper.map(patron, PatronDTO.class);
    }

    private Patron convertToEntity(PatronRequest patronRequest) {
        return modelMapper.map(patronRequest, Patron.class);
    }


}
