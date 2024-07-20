package com.rbux.library.management.system.controller;

import com.rbux.library.management.system.dto.BookBorrowerDto;
import com.rbux.library.management.system.dto.BookBorrowerResponseDto;
import com.rbux.library.management.system.service.impl.BookBorrowerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
@Slf4j
public class BookBorrowController {

    private final BookBorrowerService bookBorrowerService;

    /**
     * endpoint to borrow a book
     * @param bookBorrower payload to borrow book
     * @return borrowed book
     */
    @PostMapping("/borrow")
    public ResponseEntity<BookBorrowerResponseDto> borrowBook(@Valid @RequestBody BookBorrowerDto bookBorrower) {
        log.info("borrow book request {} ",bookBorrower);
        return new ResponseEntity<>(bookBorrowerService.borrowBook(bookBorrower), HttpStatus.CREATED);
    }

    /**
     * API to return borrowed book
     * @param bookBorrower payload for return the borrowed book
     * @return borrowed book which is returned
     */
    @PutMapping("/return")
    public ResponseEntity<BookBorrowerResponseDto> returnBook(@Valid @RequestBody BookBorrowerDto bookBorrower) {
        log.info("return borrowed book request {} ",bookBorrower);
        return new ResponseEntity<>(bookBorrowerService.returnBook(bookBorrower), HttpStatus.OK);
    }

    /**
     * API to get list of borrowed books
     * @return list of borrowed books
     */
    @GetMapping("/borrowed-books")
    public ResponseEntity<List<BookBorrowerResponseDto>> borrowedBooks() {
        return new ResponseEntity<>(bookBorrowerService.getAllBookBorrowed(), HttpStatus.OK);
    }
}
