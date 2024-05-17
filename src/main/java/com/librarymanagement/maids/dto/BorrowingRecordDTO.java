package com.librarymanagement.maids.dto;

import com.librarymanagement.maids.domain.Book;
import com.librarymanagement.maids.domain.Patron;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingRecordDTO implements Serializable {
    private Long id;
    private BookDto book;
    private PatronDTO patron;
    private Date borrowingDate;
    private Date returnDate;
    private Date actualReturnDate;
}
