package com.librarymanagement.maids;


import com.librarymanagement.maids.controller.BorrowingRecordController;
import com.librarymanagement.maids.dto.BookDto;
import com.librarymanagement.maids.dto.BorrowingRecordDTO;
import com.librarymanagement.maids.dto.PatronDTO;
import com.librarymanagement.maids.service.BorrowingRecordService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

public class BorrowingRecordControllerTest {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Mock
    private BorrowingRecordService borrowingRecordService;

    @InjectMocks
    private BorrowingRecordController borrowingRecordController;

    @Test
    public void testGetAllBorrowingRecords() {
        // Mocking borrowing record data
        BorrowingRecordDTO borrowingRecord1 = new BorrowingRecordDTO();
        borrowingRecord1.setId(1L);
        borrowingRecord1.setBook(new BookDto());
        borrowingRecord1.setPatron(new PatronDTO());
        Date borrowingDate;
        Date borrowingDate1;
        Date returngDate;
        Date returnDate1;
        try {
            borrowingDate = dateFormat.parse("2024-05-01");
            borrowingDate1 = dateFormat.parse("2024-05-03");
             returngDate = dateFormat.parse("2024-05-10");
             returnDate1 = dateFormat.parse("2024-05-12");
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the parse exception
            fail("Failed to parse borrowing date"); // Fail the test explicitly
            return;
        }

        borrowingRecord1.setBorrowingDate(borrowingDate);
        borrowingRecord1.setReturnDate(returngDate);

        BorrowingRecordDTO borrowingRecord2 = new BorrowingRecordDTO();
        borrowingRecord2.setId(2L);
        borrowingRecord1.setBook(new BookDto());
        borrowingRecord2.setPatron(new PatronDTO());
        borrowingRecord2.setBorrowingDate(borrowingDate1);
        borrowingRecord2.setReturnDate(returnDate1);

        List<BorrowingRecordDTO> borrowingRecords = Arrays.asList(borrowingRecord1, borrowingRecord2);

        // Mocking service method
        when(borrowingRecordService.getAllBorrowingRecords()).thenReturn(borrowingRecords);

        // Calling controller method
        ResponseEntity<List<BorrowingRecordDTO>> responseEntity = borrowingRecordController.getAllBorrowingRecords();

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }
    @Test
    public void testGetAllBorrowingRecords_EmptyList() {
        // Mocking an empty list of borrowing records
        List<BorrowingRecordDTO> borrowingRecords = Arrays.asList();

        // Mocking service method
        when(borrowingRecordService.getAllBorrowingRecords()).thenReturn(borrowingRecords);

        // Calling controller method
        ResponseEntity<List<BorrowingRecordDTO>> responseEntity = borrowingRecordController.getAllBorrowingRecords();

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody().size());
    }

    @Test
    public void testGetAllBorrowingRecords_NullList() {
        // Mocking a null list of borrowing records
        List<BorrowingRecordDTO> borrowingRecords = null;

        // Mocking service method
        when(borrowingRecordService.getAllBorrowingRecords()).thenReturn(borrowingRecords);

        // Calling controller method
        ResponseEntity<List<BorrowingRecordDTO>> responseEntity = borrowingRecordController.getAllBorrowingRecords();

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody().size());
    }
}

