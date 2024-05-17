package com.librarymanagement.maids.domain.RequestModel;

import com.librarymanagement.maids.domain.BookFormat;
import com.librarymanagement.maids.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    @NotBlank
    private String title;

    @NotBlank
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

    public BookDto toDto() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(this.title);
        // Set other properties if needed
        return bookDto;
    }
}
