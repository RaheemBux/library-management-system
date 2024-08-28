package com.rbux.library.management.system.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BorrowerServiceTest {

    @Mock
    private BorrowerRepository borrowerRepository;

    @InjectMocks
    private BorrowerService borrowerService;

    private Borrower borrower;
    private BorrowerDto borrowerDto;

    @BeforeEach
    void setUp() {
        borrowerDto = new BorrowerDto();
        borrowerDto.setId(1L);
        borrowerDto.setEmail("borrower1@gmail.com");
        borrowerDto.setName("Borrower1");

        borrower = new Borrower();
        borrower.setId(1L);
        borrower.setEmail("borrower1@gmail.com");
        borrower.setName("Borrower1");
    }
    @Test
    void testCreateBorrower(){
        when(borrowerRepository.save(any(Borrower.class))).thenReturn(borrower);
        BorrowerDto result = borrowerService.createBorrower(borrowerDto);
        assertEquals(borrowerDto.getName(), result.getName());
        assertEquals(borrowerDto.getEmail(), result.getEmail());
        verify(borrowerRepository).save(any(Borrower.class));
    }
    @Test
    void testGetAllBorrower(){
        List<Borrower> borrowers = Collections.singletonList(borrower);
        when(borrowerRepository.findAll()).thenReturn(borrowers);
        List<BorrowerDto> result = borrowerService.getAllBorrowers();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(borrowerDto.getEmail(), result.get(0).getEmail());
        verify(borrowerRepository, times(1)).findAll();
    }
}
