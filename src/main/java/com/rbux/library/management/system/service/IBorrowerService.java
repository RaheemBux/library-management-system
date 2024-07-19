package com.rbux.library.management.system.service;

import com.rbux.library.management.system.dto.BorrowerDto;

import java.util.List;

public interface IBorrowerService {
    BorrowerDto createBorrower(BorrowerDto borrowerDto);
    List<BorrowerDto> getAllBorrowers();
}
