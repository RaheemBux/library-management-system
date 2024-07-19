package com.rbux.library.management.system.mapper;

import com.rbux.library.management.system.dto.BorrowerDto;
import com.rbux.library.management.system.entity.Borrower;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BorrowerMapper {

    BorrowerMapper BORROWER_MAPPER = Mappers.getMapper(BorrowerMapper.class);

    Borrower toBorrower(BorrowerDto bookDto);
    BorrowerDto toBorrowerDto(Borrower book);
    List<BorrowerDto> toBorrowerDtoList(List<Borrower> books);
}

