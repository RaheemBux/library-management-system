package com.rbux.library.management.system.controller;

import com.rbux.library.management.system.dto.BookBorrowerDto;
import com.rbux.library.management.system.dto.BookBorrowerResponseDto;
import com.rbux.library.management.system.service.impl.BookBorrowerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookBorrowController {

    private final BookBorrowerService bookBorrowerService;

    @PostMapping("/borrow")
    public ResponseEntity<BookBorrowerResponseDto> borrowBook(@Valid @RequestBody BookBorrowerDto bookBorrower) {
        return new ResponseEntity<>(bookBorrowerService.borrowBook(bookBorrower), HttpStatus.CREATED);
    }

    @PutMapping("/return")
    public ResponseEntity<BookBorrowerResponseDto> returnBook(@Valid @RequestBody BookBorrowerDto bookBorrower) {
        return new ResponseEntity<>(bookBorrowerService.returnBook(bookBorrower), HttpStatus.OK);
    }
    @GetMapping("/borrowed-books")
    public ResponseEntity<List<BookBorrowerResponseDto>> borrowedBooks() {
        return new ResponseEntity<>(bookBorrowerService.getAllBookBorrowed(), HttpStatus.OK);
    }
}
