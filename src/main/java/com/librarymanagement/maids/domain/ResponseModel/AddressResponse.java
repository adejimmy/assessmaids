package com.librarymanagement.maids.domain.ResponseModel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private Long id;
    private String street;
    private String city;
    private String zipCode;
}
