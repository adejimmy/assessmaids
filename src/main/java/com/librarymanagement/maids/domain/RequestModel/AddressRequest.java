package com.librarymanagement.maids.domain.RequestModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String zipCode;
}
