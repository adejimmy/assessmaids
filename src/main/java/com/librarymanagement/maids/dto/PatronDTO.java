package com.librarymanagement.maids.dto;

import com.librarymanagement.maids.domain.PatronStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PatronDTO implements Serializable {

    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private AddressDTO address;
    private String email;
    private String phoneNumber;
    private String libraryCardNumber;
    private PatronStatus membershipStatus;
}
