package com.librarymanagement.maids.controller;

import com.librarymanagement.maids.domain.Book;
import com.librarymanagement.maids.domain.BorrowingRecord;
import com.librarymanagement.maids.domain.Patron;
import com.librarymanagement.maids.domain.RequestModel.BorrowingRecordRequest;
import com.librarymanagement.maids.dto.BorrowingRecordDTO;
import com.librarymanagement.maids.exception.ResourceNotFoundException;

import com.librarymanagement.maids.service.BookService;
import com.librarymanagement.maids.service.BorrowingRecordService;
import com.librarymanagement.maids.service.PatronService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowingRecordController {

    private final BorrowingRecordService borrowingRecordService;
    private static final Logger log = LoggerFactory.getLogger(BorrowingRecordController.class);


    public BorrowingRecordController(BorrowingRecordService borrowingRecordService) {
        this.borrowingRecordService = borrowingRecordService;
    }


    @Autowired
    private BookService bookService;

    @Autowired
    private PatronService patronService;



    @PostMapping
    public ResponseEntity<String> borrowBook(@RequestBody BorrowingRecordRequest request) {
        borrowingRecordService.borrowBook(request);
        return ResponseEntity.ok("Book borrowed successfully.");
    }


    @GetMapping
    public ResponseEntity<List<BorrowingRecordDTO>> getAllBorrowingRecords() {
        List<BorrowingRecordDTO> borrowingRecords = borrowingRecordService.getAllBorrowingRecords();
        return ResponseEntity.ok(borrowingRecords);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingRecordDTO> getBorrowingRecordById(@PathVariable Long id) {
        BorrowingRecordDTO borrowingRecordDTO = borrowingRecordService.getBorrowingRecordById(id);
        return ResponseEntity.ok(borrowingRecordDTO);
    }



    public ResponseEntity<BorrowingRecordDTO> createBorrowingRecord(@RequestBody BorrowingRecordRequest borrowingRecordRequest) {
        try {
            BorrowingRecordDTO createdRecord = borrowingRecordService.createBorrowingRecord(borrowingRecordRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRecord);
        } catch (Exception e) {
            log.error("Error creating borrowing record", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowingRecordDTO> updateBorrowingRecord(@PathVariable Long id, @RequestBody BorrowingRecordRequest borrowingRecordRequest) {
        try {
            BorrowingRecordDTO updatedRecord = borrowingRecordService.updateBorrowingRecord(id, borrowingRecordRequest);
            return ResponseEntity.ok(updatedRecord);
        } catch (ResourceNotFoundException e) {
            log.error("Borrowing record not found with ID: {}", id, e);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error updating borrowing record with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowingRecord(@PathVariable Long id) {
        try {
            borrowingRecordService.deleteBorrowingRecord(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            log.error("Borrowing record not found with ID: {}", id, e);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error deleting borrowing record with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
