package com.librarymanagement.maids.domain.ResponseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingRecordResponse {
    private Long id;
    private Date borrowedDate;
    private Date dueDate;
    private Date returnedDate;
}

