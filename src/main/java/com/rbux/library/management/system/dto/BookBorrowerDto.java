package com.rbux.library.management.system.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookBorrowerDto {

    @NotNull(message = "bookId is required")
    private Long bookId;
    @NotNull(message = "borrowerId is required")
    private Long borrowerId;
    private String borrowDate;
    private String returnDate;
}
