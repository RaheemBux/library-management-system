package com.rbux.library.management.system.controller;

import com.rbux.library.management.system.dto.BorrowerDto;
import com.rbux.library.management.system.service.IBorrowerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/borrower")
@RequiredArgsConstructor
public class BorrowerController {

    private final IBorrowerService borrowerService;

    @PostMapping
    public ResponseEntity<BorrowerDto> createBorrower(@Valid @RequestBody BorrowerDto borrower) {
        BorrowerDto savedBorrower = borrowerService.createBorrower(borrower);
        return new ResponseEntity<>(savedBorrower, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<BorrowerDto>> getAllBorrowers(){
        return new ResponseEntity<>(borrowerService.getAllBorrowers(),HttpStatus.OK);
    }
}
