package com.librarymanagement.maids.repository;

import com.librarymanagement.maids.domain.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRecordRepository  extends JpaRepository<BorrowingRecord, Long> {
}
