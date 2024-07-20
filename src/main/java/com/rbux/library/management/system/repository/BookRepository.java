package com.rbux.library.management.system.repository;

import com.rbux.library.management.system.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByIsbn(String isbn);
    List<Book> findAllByIsBorrowedTrue();
    Optional<Book> findByIdAndIsBorrowedTrue(Long id);
}
