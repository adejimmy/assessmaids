package com.librarymanagement.maids.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Unique identifier for each book

    @Column(name = "title", nullable = false)
    private String title;

    private String author; // Person who wrote or created the content of the book

    private int publicationYear; // Year when the book was published

    private String isbn; // International Standard Book Number

    private String publisher; // Company or entity responsible for producing and distributing the book

    private String genre; // Classification of the book based on its content

    private String language; // Language in which the book is written

    private int edition; // Specific version or iteration of the book

    private int pageCount; // Total number of pages in the book

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BorrowingRecord> borrowingRecords = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private BookFormat format = BookFormat.DIGITAL; // Physical or digital format of the book

    private String synopsis; // Brief overview or description of the book's content

    private double averageRating; // Average rating of the book based on reviews

    private boolean available; // Availability of the book


}
