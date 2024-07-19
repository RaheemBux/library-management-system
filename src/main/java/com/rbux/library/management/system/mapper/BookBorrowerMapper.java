package com.rbux.library.management.system.mapper;

import com.rbux.library.management.system.dto.BookBorrowerDto;
import com.rbux.library.management.system.dto.BookBorrowerResponseDto;
import com.rbux.library.management.system.entity.BookBorrower;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookBorrowerMapper {

    BookBorrowerMapper BOOK_BORROWER_MAPPER = Mappers.getMapper(BookBorrowerMapper.class);

    BookBorrower toBookBorrower(BookBorrowerDto bookBorrowerDto);
    BookBorrowerResponseDto toBookBorrowerDto(BookBorrower bookBorrower);
    List<BookBorrowerResponseDto> toBookBorrowerDtoList(List<BookBorrower> bookBorrowerList);
}

