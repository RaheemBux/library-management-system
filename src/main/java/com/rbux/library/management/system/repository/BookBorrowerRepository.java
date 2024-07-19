package com.rbux.library.management.system.repository;

import com.rbux.library.management.system.entity.Book;
import com.rbux.library.management.system.entity.BookBorrower;
import com.rbux.library.management.system.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookBorrowerRepository extends JpaRepository<BookBorrower,Long> {
    Optional<BookBorrower> findByBookAndBorrowerAndReturnDateIsNull(Book book, Borrower borrower);
}
