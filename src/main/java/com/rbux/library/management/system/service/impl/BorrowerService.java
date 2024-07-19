package com.rbux.library.management.system.service.impl;

import com.rbux.library.management.system.dto.BorrowerDto;
import com.rbux.library.management.system.entity.Borrower;
import com.rbux.library.management.system.mapper.BorrowerMapper;
import com.rbux.library.management.system.repository.BorrowerRepository;
import com.rbux.library.management.system.service.IBorrowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowerService implements IBorrowerService {

    private final BorrowerRepository borrowerRepository;

    @Override
    public BorrowerDto createBorrower(BorrowerDto borrowerDto) {
        Borrower borrower = BorrowerMapper.BORROWER_MAPPER.toBorrower(borrowerDto);
        return BorrowerMapper.BORROWER_MAPPER.toBorrowerDto(borrowerRepository.save(borrower));
    }

    @Override
    public List<BorrowerDto> getAllBorrowers() {
        return BorrowerMapper.BORROWER_MAPPER.toBorrowerDtoList(borrowerRepository.findAll());
    }
}
