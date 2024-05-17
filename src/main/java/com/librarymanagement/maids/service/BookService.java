package com.librarymanagement.maids.service;



import com.librarymanagement.maids.domain.Book;
import com.librarymanagement.maids.domain.RequestModel.BookRequest;
import com.librarymanagement.maids.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();
    BookDto getBookById(Long id);
    BookDto createBook(BookRequest bookRequest);
    BookDto updateBook(Long id, BookRequest bookRequest);
    void deleteBook(Long id);

    Book getBookByIds(Long id);
}
