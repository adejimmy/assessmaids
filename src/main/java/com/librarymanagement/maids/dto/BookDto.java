package com.librarymanagement.maids.dto;

import com.librarymanagement.maids.domain.BookFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto implements Serializable {

    private Long id;
    private String title;
    private String author;
    private int publicationYear;
    private String isbn;
    private String publisher;
    private String genre;
    private String language;
    private int edition;
    private int pageCount;
    private Set<BorrowingRecordDTO> borrowingRecords;
    private BookFormat format;
    private String synopsis;
    private double averageRating;
    private boolean available;
}
