package com.librarymanagement.maids.service;

import com.librarymanagement.maids.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    AddressDTO getAddressById(Long id);

    List<AddressDTO> getAllAddresses();

    AddressDTO createAddress(AddressDTO addressDto);

    AddressDTO updateAddress(Long id, AddressDTO addressDto);

    void deleteAddress(Long id);
}
