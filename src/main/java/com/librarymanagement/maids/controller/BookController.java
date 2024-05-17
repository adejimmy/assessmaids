package com.librarymanagement.maids.controller;

import com.librarymanagement.maids.domain.RequestModel.BookRequest;
import com.librarymanagement.maids.domain.ResponseModel.BookResponse;
import com.librarymanagement.maids.dto.BookDto;
import com.librarymanagement.maids.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> bookDtos = bookService.getAllBooks();
        return ResponseEntity.ok(bookDtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        BookDto bookDto = bookService.getBookById(id);
        return ResponseEntity.ok(bookDto);
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookRequest bookRequest) {
        BookDto createdBook = bookService.createBook(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        BookDto updatedBook = bookService.updateBook(id, bookRequest);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }


//
private BookResponse convertToResponse(BookDto bookDto) {
    // Implement logic to map BookDto to BookResponse

    return new BookResponse(
            bookDto.getId(),
            bookDto.getTitle(),
            bookDto.getAuthor(),
            0, // Default publicationYear value
            "", // Default publisher value
            bookDto.getIsbn(),
            bookDto.getGenre(),
            bookDto.getLanguage(),
            bookDto.getEdition(),
            bookDto.getPageCount(),
            bookDto.getFormat(),
            bookDto.getSynopsis(),
            bookDto.getAverageRating(),
            bookDto.isAvailable()

    );
}
}
