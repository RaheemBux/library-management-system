package com.rbux.library.management.system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookDto {

    private Long id;
    @NotBlank(message = "Book isbn is required")
    private String isbn;
    @NotBlank(message = "Book title is required")
    private String title;
    @NotBlank(message = "Book author is required")
    private String author;
    private boolean isBorrowed;
}
