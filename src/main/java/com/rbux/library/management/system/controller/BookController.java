package com.rbux.library.management.system.controller;

import com.rbux.library.management.system.dto.BookDto;
import com.rbux.library.management.system.service.IBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final IBookService bookService;

    /**
     * API to register new book
     * @param book book payload to register
     * @return newly registered book
     */
    @PostMapping
    public ResponseEntity<BookDto> registerBook(@Valid @RequestBody BookDto book) {
        log.info("register a new book {} ",book);
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    /**
     * API to get all books
     * @param isBorrowed boolean param to get borrowed or not borrowed books
     * @return list of books
     */
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(@RequestParam(value = "is_borrowed",required = false) boolean isBorrowed){
        log.info("Get all books having is_borrowed {} ",isBorrowed);
        if(isBorrowed){
            return new ResponseEntity<>(bookService.getAllBorrowedBooks(),HttpStatus.OK);
        }
        return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
    }
}
