package com.rbux.library.management.system.controller;

import com.rbux.library.management.system.dto.BorrowerDto;
import com.rbux.library.management.system.service.IBorrowerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/borrower")
@RequiredArgsConstructor
@Slf4j
public class BorrowerController {

    private final IBorrowerService borrowerService;

    /**
     * API to create new borrower
     * @param borrower borrower payload
     * @return added borrower
     */
    @PostMapping
    public ResponseEntity<BorrowerDto> createBorrower(@Valid @RequestBody BorrowerDto borrower) {
        log.info("create a new borrower {} ",borrower);
        return new ResponseEntity<>(borrowerService.createBorrower(borrower), HttpStatus.CREATED);
    }

    /**
     * API to get lis of borrowers
     * @return list of borrower
     */
    @GetMapping
    public ResponseEntity<List<BorrowerDto>> getAllBorrowers(){
        log.info("get all borrowers");
        return new ResponseEntity<>(borrowerService.getAllBorrowers(),HttpStatus.OK);
    }
}
