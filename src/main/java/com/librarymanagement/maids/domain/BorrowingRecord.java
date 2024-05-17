package com.librarymanagement.maids.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity
@Table(name="borrowed_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Unique identifier for each borrowing record

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id", nullable = false)
    private Patron patron;

    @Column(name = "borrowed_date", nullable = false)
    private Date borrowedDate;

    @Column(name = "returned_date")
    private Date returnedDate;

    private Date actualReturnDate; // Actual return date for the book
}
