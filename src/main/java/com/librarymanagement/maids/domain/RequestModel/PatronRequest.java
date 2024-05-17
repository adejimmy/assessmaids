package com.librarymanagement.maids.domain.RequestModel;

import com.librarymanagement.maids.domain.PatronStatus;
import com.librarymanagement.maids.dto.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatronRequest {
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
