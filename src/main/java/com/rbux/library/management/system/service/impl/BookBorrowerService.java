package com.rbux.library.management.system.service.impl;

import com.rbux.library.management.system.dto.BookBorrowerDto;
import com.rbux.library.management.system.dto.BookBorrowerResponseDto;
import com.rbux.library.management.system.entity.Book;
import com.rbux.library.management.system.entity.BookBorrower;
import com.rbux.library.management.system.entity.Borrower;
import com.rbux.library.management.system.mapper.BookBorrowerMapper;
import com.rbux.library.management.system.repository.BookBorrowerRepository;
import com.rbux.library.management.system.repository.BookRepository;
import com.rbux.library.management.system.repository.BorrowerRepository;
import com.rbux.library.management.system.service.IBookBorrowedService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookBorrowerService implements IBookBorrowedService {

    private final BookBorrowerRepository bookBorrowerRepository;
    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;

    /**
     * Method to add borrow a book
     * @param bookBorrowerDto BookBorrowerDto
     * @return BookBorrowerResponseDto
     */
    @Override
    @Transactional
    public BookBorrowerResponseDto borrowBook(BookBorrowerDto bookBorrowerDto) {
        // validate book and borrower
        BookBorrower bookBorrower = validateAndGet(bookBorrowerDto.getBookId(),bookBorrowerDto.getBorrowerId());
        // validate if books is already borrowed
        Optional<Book> optionalBook = bookRepository.findByIdAndIsBorrowedTrue(bookBorrower.getBook().getId());
        if(optionalBook.isPresent()){
            log.error("This book is currently borrowed by the borrower");
            throw new IllegalStateException("This book is currently borrowed by the borrower");
        }
        bookBorrower.setBorrowDate(LocalDateTime.now());
        Book book = bookBorrower.getBook();
        book.setBorrowed(Boolean.TRUE);
        bookRepository.save(book);
        BookBorrower savedBorrowRecord = bookBorrowerRepository.save(bookBorrower);
        log.info("borrowed book saved into database {} ",savedBorrowRecord);
        return BookBorrowerMapper.BOOK_BORROWER_MAPPER.toBookBorrowerDto(savedBorrowRecord);
    }

    /**
     * Method to return borrowed book
     * @param bookBorrowerDto BookBorrowerDto
     * @return BookBorrowerResponseDto
     */
    @Override
    @Transactional
    public BookBorrowerResponseDto returnBook(BookBorrowerDto bookBorrowerDto) {
        // validate book and borrower
        BookBorrower validedBookBorrower = validateAndGet(bookBorrowerDto.getBookId(),bookBorrowerDto.getBorrowerId());
        Book book = validedBookBorrower.getBook();
        // check if books is borrowed or not
        BookBorrower bookBorrower = bookBorrowerRepository.findByBookAndBorrowerAndReturnDateIsNull(book, validedBookBorrower.getBorrower())
                .orElseThrow(() -> new IllegalStateException("This book is not currently borrowed by the borrower"));
        bookBorrower.setReturnDate(LocalDateTime.now());
        book.setBorrowed(Boolean.FALSE);
        BookBorrower updatedBookBorrower = bookBorrowerRepository.save(bookBorrower);
        bookRepository.save(book);
        return BookBorrowerMapper.BOOK_BORROWER_MAPPER.toBookBorrowerDto(updatedBookBorrower);
    }

    /**
     * Method to get list of borrowed books
     * @return List<BookBorrowerResponseDto>
     */
    @Override
    public List<BookBorrowerResponseDto> getAllBookBorrowed() {
        return BookBorrowerMapper.BOOK_BORROWER_MAPPER.toBookBorrowerDtoList(bookBorrowerRepository.findAll());
    }

    private BookBorrower validateAndGet(Long bookId,Long borrowerId){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()->new RuntimeException("book with id "+bookId+" not found"));
        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new RuntimeException("borrower with id "+borrowerId+" not found"));
        BookBorrower bookBorrower = new BookBorrower();
        bookBorrower.setBook(book);
        bookBorrower.setBorrower(borrower);
        return bookBorrower;
    }
}

