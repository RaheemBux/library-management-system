package com.rbux.library.management.system.service;

import com.rbux.library.management.system.dto.BookDto;
import com.rbux.library.management.system.entity.Book;
import com.rbux.library.management.system.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookBorrowServiceTest {

    @Mock
    private BookBorrowerRepository bookBorrowerRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BorrowerRepository borrowerRepository;

    @InjectMocks
    private BookBorrowerService bookBorrowerService;

    private BookBorrower bookBorrower;
    private BookBorrowerDto bookBorrowerDto;
    private BookBorrowerResponseDto bookBorrowerResponseDto;
    private Book book;
    private BookDto bookDto;
    private BorrowerDto borrowerDto;
    private Borrower borrower;

    @BeforeEach
    void setUp(){
        bookBorrower = new BookBorrower();
        bookBorrowerDto = new BookBorrowerDto();
        bookBorrowerResponseDto = new BookBorrowerResponseDto();
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

        borrowerDto = new BorrowerDto();
        borrowerDto.setId(1L);
        borrowerDto.setEmail("borrower1@gmail.com");
        borrowerDto.setName("Borrower1");

        borrower = new Borrower();
        borrower.setId(1L);
        borrower.setEmail("borrower1@gmail.com");
        borrower.setName("Borrower1");

        bookBorrowerDto.setBookId(1L);
        bookBorrowerDto.setBorrowerId(1L);

        bookBorrowerResponseDto.setBorrower(borrowerDto);
        bookBorrowerResponseDto.setBook(bookDto);
        bookBorrowerResponseDto.setBorrowDate(LocalDateTime.now().toString());

    }
    @Test
    void testBorrowBook() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(borrowerRepository.findById(borrower.getId())).thenReturn(Optional.of(borrower));
        when(bookRepository.findByIdAndIsBorrowedTrue(book.getId())).thenReturn(Optional.empty());
        when(bookBorrowerRepository.save(any(BookBorrower.class))).thenReturn(bookBorrower);
        BookBorrowerResponseDto responseDto = bookBorrowerService.borrowBook(bookBorrowerDto);
        assertNotNull(responseDto);
        verify(bookRepository).save(book);
        verify(bookBorrowerRepository).save(any(BookBorrower.class));
    }
    @Test
    void testBorrowBookWhenBookIsAlreadyBorrowed() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(borrowerRepository.findById(borrower.getId())).thenReturn(Optional.of(borrower));
        when(bookRepository.findByIdAndIsBorrowedTrue(book.getId())).thenReturn(Optional.of(book));
        assertThrows(IllegalStateException.class, () -> bookBorrowerService.borrowBook(bookBorrowerDto));
    }
    @Test
    void testReturnBorrowedBook() {
        book.setBorrowed(true);
        bookBorrower.setBorrowDate(LocalDateTime.now());
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(borrowerRepository.findById(borrower.getId())).thenReturn(Optional.of(borrower));
        when(bookBorrowerRepository.findByBookAndBorrowerAndReturnDateIsNull(book, borrower)).thenReturn(Optional.of(bookBorrower));
        when(bookBorrowerRepository.save(any(BookBorrower.class))).thenReturn(bookBorrower);
        BookBorrowerResponseDto responseDto = bookBorrowerService.returnBook(bookBorrowerDto);
        assertNotNull(responseDto);
        assertNotNull(bookBorrower.getReturnDate());
        verify(bookRepository).save(book);
        verify(bookBorrowerRepository).save(any(BookBorrower.class));
    }

    @Test
    void testReturnBookWhenBookIsNotBorrowed() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(borrowerRepository.findById(borrower.getId())).thenReturn(Optional.of(borrower));
        when(bookBorrowerRepository.findByBookAndBorrowerAndReturnDateIsNull(book, borrower)).thenReturn(Optional.empty());
        assertThrows(IllegalStateException.class, () -> bookBorrowerService.returnBook(bookBorrowerDto));
    }

    @Test
    void testGetAllBookBorrowed() {
        when(bookBorrowerRepository.findAll()).thenReturn(Collections.singletonList(bookBorrower));
        List<BookBorrowerResponseDto> responseDtoList = bookBorrowerService.getAllBookBorrowed();
        assertNotNull(responseDtoList);
        assertEquals(1, responseDtoList.size());
    }

}
