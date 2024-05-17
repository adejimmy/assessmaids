package com.librarymanagement.maids.domain.RequestModel;

import com.librarymanagement.maids.dto.BookDto;
import com.librarymanagement.maids.dto.PatronDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingRecordRequest {
    private Long id;
    private Long bookId;
    private Long patronId;
    private Date borrowingDate;
    private Date returnDate;
    private Date actualReturnDate;
}
