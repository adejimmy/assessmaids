package com.librarymanagement.maids.serviceImplementation;

import com.librarymanagement.maids.domain.Address;
import com.librarymanagement.maids.dto.AddressDTO;
import com.librarymanagement.maids.repository.AddressRepository;
import com.librarymanagement.maids.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    @Cacheable(value = "addresses", key = "#id")
    @Transactional(readOnly = true)
    public AddressDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
        return convertToDto(address);
    }

    @Override
    @Cacheable(value = "addresses")
    @Transactional(readOnly = true)
    public List<AddressDTO> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @CacheEvict(value = "addresses", allEntries = true)
    public AddressDTO createAddress(AddressDTO addressDto) {
        Address address = convertToEntity(addressDto);
        Address savedAddress = addressRepository.save(address);
        log.info("Created new address with ID: {}", savedAddress.getId());
        return convertToDto(savedAddress);
    }

    @Override
    @Transactional
    @CacheEvict(value = "addresses", key = "#id")
    public AddressDTO updateAddress(Long id, AddressDTO addressDto) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));

        // Update fields from the DTO
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setZipcode(addressDto.getZipcode());

        Address updatedAddress = addressRepository.save(address);
        log.info("Updated address with ID: {}", updatedAddress.getId());
        return convertToDto(updatedAddress);
    }

    @Override
    @Transactional
    @CacheEvict(value = "addresses", key = "#id")
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
        log.info("Deleted address with ID: {}", id);
    }

    private AddressDTO convertToDto(Address address) {
        AddressDTO addressDto = new AddressDTO();
        addressDto.setId(address.getId());
        addressDto.setStreet(address.getStreet());
        addressDto.setCity(address.getCity());
        addressDto.setZipcode(address.getZipcode());
        return addressDto;
    }

    private Address convertToEntity(AddressDTO addressDto) {
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setZipcode(addressDto.getZipcode());
        return address;
    }
}
