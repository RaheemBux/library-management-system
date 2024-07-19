package com.rbux.library.management.system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BorrowerDto {

    private Long id;
    @NotBlank(message = "Borrower name is required")
    private String name;
    @NotBlank(message = "Borrower email is required")
    private String email;
}
