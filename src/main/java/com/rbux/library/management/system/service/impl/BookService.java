package com.rbux.library.management.system.service.impl;

import com.rbux.library.management.system.dto.BookDto;
import com.rbux.library.management.system.entity.Book;
import com.rbux.library.management.system.mapper.BookMapper;
import com.rbux.library.management.system.repository.BookRepository;
import com.rbux.library.management.system.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final BookRepository bookRepository;

    @Override
    public BookDto createBook(BookDto bookDto) {
        if(!isValid(bookDto)){
            throw new IllegalArgumentException("Book with same isbn with different author/title can't be registered");
        }
        Book book = BookMapper.BOOK_MAPPER.toBook(bookDto);
        return BookMapper.BOOK_MAPPER.toBookDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> getAllBooks() {
        return BookMapper.BOOK_MAPPER.toBookDtoList(bookRepository.findAll());
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public List<BookDto> getAllBorrowedBooks() {
        return BookMapper.BOOK_MAPPER.toBookDtoList(bookRepository.findAllByIsBorrowedTrue());
    }

    private boolean isValid(BookDto bookDto){
        Optional<Book> optionalBook = getBookByIsbn(bookDto.getIsbn());
        if(optionalBook.isPresent()){
            Book existingBook = optionalBook.get();
            return existingBook.getAuthor().equals(bookDto.getAuthor()) && existingBook.getTitle().equals(bookDto.getTitle());
        }
        return true;
    }
}
