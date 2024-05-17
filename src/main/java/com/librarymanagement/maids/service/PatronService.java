package com.librarymanagement.maids.service;

import com.librarymanagement.maids.domain.Patron;
import com.librarymanagement.maids.domain.RequestModel.PatronRequest;
import com.librarymanagement.maids.dto.PatronDTO;

import java.util.List;

public interface PatronService {

    List<PatronDTO> getAllPatrons();
    PatronDTO getPatronById(Long id);
    PatronDTO createPatron(PatronRequest patronRequest);
    PatronDTO updatePatron(Long id, PatronRequest patronRequest);
    void deletePatron(Long id);

    Patron getPatronByIds(Long id);

}
