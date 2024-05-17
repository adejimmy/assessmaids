package com.librarymanagement.maids.service;

import com.librarymanagement.maids.domain.BorrowingRecord;
import com.librarymanagement.maids.domain.RequestModel.BorrowingRecordRequest;
import com.librarymanagement.maids.dto.BorrowingRecordDTO;

import java.util.List;

public interface BorrowingRecordService {
    List<BorrowingRecordDTO> getAllBorrowingRecords();
    BorrowingRecordDTO getBorrowingRecordById(Long id);
    BorrowingRecordDTO createBorrowingRecord(BorrowingRecordRequest borrowingRecordRequest);
    BorrowingRecordDTO updateBorrowingRecord(Long id, BorrowingRecordRequest borrowingRecordRequest);
    void deleteBorrowingRecord(Long id);
    BorrowingRecord saveBorrowingRecord(BorrowingRecord borrowingRecord);

    void saveBorrowingRecord1(BorrowingRecordDTO borrowingRecordDTO);

    void borrowBook(BorrowingRecordRequest request);


}
