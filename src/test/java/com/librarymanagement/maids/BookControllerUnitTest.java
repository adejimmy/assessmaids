package com.librarymanagement.maids;

import com.librarymanagement.maids.controller.BookController;
import com.librarymanagement.maids.domain.RequestModel.BookRequest;
import com.librarymanagement.maids.dto.BookDto;
import com.librarymanagement.maids.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookControllerUnitTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        // Mocking book data
        BookDto book1 = new BookDto();
        book1.setId(1L);
        book1.setTitle("Book 1");

        BookDto book2 = new BookDto();
        book2.setId(2L);
        book2.setTitle("Book 2");

        List<BookDto> books = Arrays.asList(book1, book2);

        // Mocking service method
        when(bookService.getAllBooks()).thenReturn(books);

        // Calling controller method
        ResponseEntity<List<BookDto>> responseEntity = bookController.getAllBooks();

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }



    @Test
    public void testGetBookById() {
        // Mocking book data
        BookDto book = new BookDto();
        book.setId(1L);
        book.setTitle("Book 1");

        // Mocking service method
        when(bookService.getBookById(1L)).thenReturn(book);

        // Calling controller method
        ResponseEntity<BookDto> responseEntity = bookController.getBookById(1L);

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(book, responseEntity.getBody());
    }



    @Test
    public void testCreateBook() {
        // Mocking book request data
        BookRequest bookRequest = new BookRequest();
        bookRequest.setTitle("New Book");

        // Mocking service method
        when(bookService.createBook(bookRequest)).thenReturn(bookRequest.toDto()); // Assuming there's a method toDto() in BookRequest

        // Calling controller method
        ResponseEntity<BookDto> responseEntity = bookController.createBook(bookRequest);

        // Verifying the response
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(bookRequest.toDto(), responseEntity.getBody());
    }


    @Test
    public void testUpdateBook() {
        // Mocking book request data
        BookRequest bookRequest = new BookRequest();
        bookRequest.setTitle("Updated Book");

        // Mocking service method
        when(bookService.updateBook(1L, bookRequest)).thenReturn(new BookDto());

        // Calling controller method
        ResponseEntity<BookDto> responseEntity = bookController.updateBook(1L, bookRequest);

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody()); // Asserting the response body is null
    }






    @Test
    public void testDeleteBook() {
        // Calling controller method
        ResponseEntity<Void> responseEntity = bookController.deleteBook(1L);

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(bookService, times(1)).deleteBook(1L);
    }
}
