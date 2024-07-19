package com.rbux.library.management.system.repository;

import com.rbux.library.management.system.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerRepository extends JpaRepository<Borrower,Long> {
}
