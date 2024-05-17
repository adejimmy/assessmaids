package com.librarymanagement.maids.domain.ResponseModel;

import com.librarymanagement.maids.domain.BookFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
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
    private BookFormat format;
    private String synopsis;
    private double averageRating;
    private boolean available;
}
