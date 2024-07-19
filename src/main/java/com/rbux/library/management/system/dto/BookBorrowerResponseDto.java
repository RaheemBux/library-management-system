package com.rbux.library.management.system.dto;

import lombok.Data;

@Data
public class BookBorrowerResponseDto {

    private BookDto book;
    private BorrowerDto borrower;
    private String borrowDate;
    private String returnDate;
}
