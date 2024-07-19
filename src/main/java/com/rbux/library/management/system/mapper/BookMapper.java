package com.rbux.library.management.system.mapper;

import com.rbux.library.management.system.dto.BookDto;
import com.rbux.library.management.system.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper BOOK_MAPPER = Mappers.getMapper(BookMapper.class);

    Book toBook(BookDto bookDto);
    BookDto toBookDto(Book book);
    List<BookDto> toBookDtoList(List<Book> books);
}

