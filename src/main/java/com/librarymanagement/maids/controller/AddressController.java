package com.librarymanagement.maids.controller;


import com.librarymanagement.maids.dto.AddressDTO;
import com.librarymanagement.maids.exception.ErrorResponse;
import com.librarymanagement.maids.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@Slf4j
@Validated
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable @Min(1) Long id) {
        try {
            AddressDTO addressDto = addressService.getAddressById(id);
            return ResponseEntity.ok(addressDto);
        } catch (Exception e) {
            log.error("Error retrieving address with ID {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Failed to retrieve address with ID " + id));
        }
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        try {
            List<AddressDTO> addresses = addressService.getAllAddresses();
            return ResponseEntity.ok(addresses);
        } catch (Exception e) {
            // Log the error if needed
            log.error("Error retrieving all addresses", e);
            // Return an empty list of addresses
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @PostMapping
    public ResponseEntity<?> createAddress(@Valid @RequestBody AddressDTO addressDto) {
        try {
            AddressDTO createdAddress = addressService.createAddress(addressDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
        } catch (Exception e) {
            log.error("Error creating address", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Failed to create address"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable @Min(1) Long id, @Valid @RequestBody AddressDTO addressDto) {
        try {
            AddressDTO updatedAddress = addressService.updateAddress(id, addressDto);
            return ResponseEntity.ok(updatedAddress);
        } catch (Exception e) {
            log.error("Error updating address with ID {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Failed to update address with ID " + id));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable @Min(1) Long id) {
        try {
            addressService.deleteAddress(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error deleting address with ID {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Failed to delete address with ID " + id));
        }
    }
}
