package com.rbux.library.management.system.service;

import com.rbux.library.management.system.dto.BookDto;
import com.rbux.library.management.system.entity.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    BookDto createBook(BookDto bookDto);
    List<BookDto> getAllBooks();
    Optional<Book> getBookByIsbn(String isbn);
    List<BookDto> getAllBorrowedBooks();
}
