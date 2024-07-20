package com.rbux.library.management.system.service;

import com.rbux.library.management.system.dto.BookDto;
import com.rbux.library.management.system.entity.Book;
import com.rbux.library.management.system.repository.BookRepository;
import com.rbux.library.management.system.service.impl.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private BookDto bookDto;
    private Book book;

    @BeforeEach
    void setUp() {
        bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setIsbn("123456789");
        bookDto.setTitle("Test Book");
        bookDto.setAuthor("Test Author");
        bookDto.setBorrowed(false);

        book = new Book();
        book.setId(1L);
        book.setIsbn("123456789");
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setBorrowed(false);
    }

    @Test
    void testCreateBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        // Act
        BookDto result = bookService.createBook(bookDto);
        // Assert
        assertEquals(bookDto.getIsbn(), result.getIsbn());
        assertEquals(bookDto.getAuthor(), result.getAuthor());
        assertEquals(bookDto.getTitle(), result.getTitle());
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    void testCreateBookInvalid() {
        book = new Book();
        book.setId(1L);
        book.setIsbn("123456789");
        book.setTitle("Test Book 2");
        book.setAuthor("Test Author 2");
        book.setBorrowed(false);
        when(bookRepository.findByIsbn(bookDto.getIsbn())).thenReturn(Optional.of(book));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> bookService.createBook(bookDto));
        assertEquals("Book with same isbn with different author/title can't be registered", exception.getMessage());
        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = Collections.singletonList(book);
        when(bookRepository.findAll()).thenReturn(books);
        List<BookDto> result = bookService.getAllBooks();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(bookDto.getIsbn(), result.get(0).getIsbn());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testGetBookByIsbn() {
        when(bookRepository.findByIsbn(any(String.class))).thenReturn(Optional.of(book));
        Optional<Book> result = bookService.getBookByIsbn("123456789");
        assertTrue(result.isPresent());
        assertEquals(book.getIsbn(), result.get().getIsbn());
        verify(bookRepository, times(1)).findByIsbn(any(String.class));
    }

    @Test
    void testGetAllBorrowedBooks() {
        book = new Book();
        book.setId(1L);
        book.setIsbn("123456789");
        book.setTitle("Test Book 2");
        book.setAuthor("Test Author 2");
        book.setBorrowed(true);
        List<Book> books = Collections.singletonList(book);
        when(bookRepository.findAllByIsBorrowedTrue()).thenReturn(books);
        List<BookDto> result = bookService.getAllBorrowedBooks();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(bookDto.getIsbn(), result.get(0).getIsbn());
        verify(bookRepository, times(1)).findAllByIsBorrowedTrue();
    }
}
