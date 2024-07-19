package com.rbux.library.management.system.controller;

import com.rbux.library.management.system.dto.BookDto;
import com.rbux.library.management.system.service.IBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    @PostMapping
    public ResponseEntity<BookDto> registerBook(@Valid @RequestBody BookDto book) {
        BookDto savedBook = bookService.createBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(@RequestParam(value = "is_borrowed",required = false) boolean isBorrowed){
        if(isBorrowed){
            return new ResponseEntity<>(bookService.getAllBorrowedBooks(),HttpStatus.OK);
        }
        return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
    }
}
