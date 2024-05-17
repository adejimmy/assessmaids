package com.librarymanagement.maids.serviceImplementation;


import com.librarymanagement.maids.domain.Book;
import com.librarymanagement.maids.domain.RequestModel.BookRequest;
import com.librarymanagement.maids.domain.ResponseModel.BookResponse;
import com.librarymanagement.maids.dto.BookDto;
import com.librarymanagement.maids.exception.ResourceNotFoundException;
import com.librarymanagement.maids.repository.BookRepository;
import com.librarymanagement.maids.service.BookService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "books")
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "books", key = "#id")
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return convertToDto(book);
    }

    public Book getBookByIds(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    @Override
    @Transactional
    @CacheEvict(value = "books", allEntries = true)
    public BookDto createBook(BookRequest bookRequest) {
        Book book = convertToEntity(bookRequest);
        Book savedBook = bookRepository.save(book);
        return convertToDto(savedBook);
    }

    @Override
    @Transactional
    @CacheEvict(value = "books", key = "#id")
    public BookDto updateBook(Long id, BookRequest bookRequest) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        // Update existingBook with data from bookRequest
        existingBook.setTitle(bookRequest.getTitle());
        // Update other attributes as needed

        Book updatedBook = bookRepository.save(existingBook);
        return convertToDto(updatedBook);
    }


    @Override
    @Transactional
    @CacheEvict(value = "books", key = "#id")
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        bookRepository.delete(book);
    }

    private BookResponse convertToResponse(Book book) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(book, BookResponse.class);
    }

    private Book convertToEntity(BookRequest bookRequest) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(bookRequest, Book.class);
    }

    private Book convertToEntity(BookDto bookDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(bookDto, Book.class);
    }

    private BookDto convertToDto(Book book) {
        BookDto bookDto = new BookDto();
        // Map attributes from Book to BookDto
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        // Map other attributes as needed
        return bookDto;
    }
}