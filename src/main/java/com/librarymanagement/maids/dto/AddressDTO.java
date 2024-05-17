package com.librarymanagement.maids.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO implements Serializable {

    private Long id;
    private String street;
    private String country;
    private String zipcode;
    private String city;
    private String countrycode;
}
