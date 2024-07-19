package com.rbux.library.management.system.service;

import com.rbux.library.management.system.dto.BookBorrowerDto;
import com.rbux.library.management.system.dto.BookBorrowerResponseDto;

import java.util.List;

public interface IBookBorrowedService {
    BookBorrowerResponseDto borrowBook(BookBorrowerDto bookBorrowerDto);
    BookBorrowerResponseDto returnBook(BookBorrowerDto bookBorrowerDto);
    List<BookBorrowerResponseDto> getAllBookBorrowed();
}
