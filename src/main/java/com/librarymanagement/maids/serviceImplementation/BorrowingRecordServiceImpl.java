package com.librarymanagement.maids.serviceImplementation;

import com.librarymanagement.maids.domain.Book;
import com.librarymanagement.maids.domain.BorrowingRecord;
import com.librarymanagement.maids.domain.Patron;
import com.librarymanagement.maids.domain.RequestModel.BorrowingRecordRequest;
import com.librarymanagement.maids.dto.BorrowingRecordDTO;
import com.librarymanagement.maids.exception.NotFoundException;
import com.librarymanagement.maids.exception.ResourceNotFoundException;
import com.librarymanagement.maids.repository.BookRepository;
import com.librarymanagement.maids.repository.BorrowingRecordRepository;
import com.librarymanagement.maids.repository.PatronRepository;
import com.librarymanagement.maids.service.BorrowingRecordService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final ModelMapper modelMapper;
    private static final Logger log = LoggerFactory.getLogger(BorrowingRecordServiceImpl.class);

    @Autowired
    public BorrowingRecordServiceImpl(BorrowingRecordRepository borrowingRecordRepository, ModelMapper modelMapper) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Cacheable(value = "borrowingRecords")
    public List<BorrowingRecordDTO> getAllBorrowingRecords() {
        log.info("Retrieving all borrowing records from the database.");
        List<BorrowingRecord> borrowingRecords = borrowingRecordRepository.findAll();
        return borrowingRecords.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "borrowingRecords", key = "#id")
    public BorrowingRecordDTO getBorrowingRecordById(Long id) {
        log.info("Retrieving borrowing record with ID: {} from the database.", id);
        BorrowingRecord borrowingRecord = borrowingRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrowing Record not found with id: " + id));
        return convertToDTO(borrowingRecord);
    }

    @Override
    @Transactional
    public BorrowingRecordDTO createBorrowingRecord(BorrowingRecordRequest borrowingRecordRequest) {
        log.info("Creating a new borrowing record.");
        BorrowingRecord borrowingRecord = convertToEntity(borrowingRecordRequest);
        BorrowingRecord savedRecord = borrowingRecordRepository.save(borrowingRecord);
        return convertToDTO(savedRecord);
    }

    @Override
    @Transactional
    public BorrowingRecordDTO updateBorrowingRecord(Long id, BorrowingRecordRequest borrowingRecordRequest) {
        log.info("Updating borrowing record with ID: {}", id);
        BorrowingRecord existingRecord = borrowingRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrowing Record not found with id: " + id));
        modelMapper.map(borrowingRecordRequest, existingRecord);
        BorrowingRecord updatedRecord = borrowingRecordRepository.save(existingRecord);
        return convertToDTO(updatedRecord);
    }

    @Override
    @Transactional
    public void deleteBorrowingRecord(Long id) {
        log.info("Deleting borrowing record with ID: {}", id);
        BorrowingRecord borrowingRecord = borrowingRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrowing Record not found with id: " + id));
        borrowingRecordRepository.delete(borrowingRecord);
    }


    @Override
    public BorrowingRecord saveBorrowingRecord(BorrowingRecord borrowingRecord) {
        return borrowingRecordRepository.save(borrowingRecord);
    }


    public void borrowBook(BorrowingRecordRequest request) {
        // Retrieve book entity by ID
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new NotFoundException("Book not found with ID: " + request.getBookId()));

        // Retrieve patron entity by ID
        Patron patron = patronRepository.findById(request.getPatronId())
                .orElseThrow(() -> new NotFoundException("Patron not found with ID: " + request.getPatronId()));

        // Create borrowing record
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowedDate(new Date());

        // Save borrowing record
        borrowingRecordRepository.save(borrowingRecord);
    }
    @Override
    public void saveBorrowingRecord1(BorrowingRecordDTO borrowingRecordDTO) {
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setId(borrowingRecordDTO.getId());
        // Set other properties

        // Get book and patron by ID
        Book book = bookRepository.findById(borrowingRecordDTO.getBook().getId()).orElse(null);
        Patron patron = patronRepository.findById(borrowingRecordDTO.getPatron().getId()).orElse(null);

        // Set book and patron
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);

        borrowingRecordRepository.save(borrowingRecord);
    }

    private BorrowingRecordDTO convertToDTO(BorrowingRecord borrowingRecord) {
        return modelMapper.map(borrowingRecord, BorrowingRecordDTO.class);
    }

    private BorrowingRecord convertToEntity(BorrowingRecordRequest borrowingRecordRequest) {
        return modelMapper.map(borrowingRecordRequest, BorrowingRecord.class);
    }
}

