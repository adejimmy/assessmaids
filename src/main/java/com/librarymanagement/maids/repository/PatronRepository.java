package com.librarymanagement.maids.repository;

import com.librarymanagement.maids.domain.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron, Long> {
}
