package com.librarymanagement.maids;


import com.librarymanagement.maids.controller.PatronController;

import com.librarymanagement.maids.dto.PatronDTO;

import com.librarymanagement.maids.service.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PatronControllerTest {

    @Mock
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;

    @Test
    public void testGetAllPatrons() {
        // Mocking patron data
        PatronDTO patron1 = new PatronDTO();
        patron1.setId(1L);
        patron1.setName("Patron 1");

        PatronDTO patron2 = new PatronDTO();
        patron2.setId(2L);
        patron2.setName("Patron 2");

        List<PatronDTO> patrons = Arrays.asList(patron1, patron2);

        // Mocking service method
        when(patronService.getAllPatrons()).thenReturn(patrons);

        // Calling controller method
        ResponseEntity<List<PatronDTO>> responseEntity = patronController.getAllPatrons();

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }



    @Test
    public void testGetAllPatrons_EmptyList() {
        // Mocking an empty list of patrons
        List<PatronDTO> patrons = Arrays.asList();

        // Mocking service method
        when(patronService.getAllPatrons()).thenReturn(patrons);

        // Calling controller method
        ResponseEntity<List<PatronDTO>> responseEntity = patronController.getAllPatrons();

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody().size());
    }

    @Test
    public void testGetAllPatrons_NullList() {
        // Mocking a null list of patrons
        List<PatronDTO> patrons = null;

        // Mocking service method
        when(patronService.getAllPatrons()).thenReturn(patrons);

        // Calling controller method
        ResponseEntity<List<PatronDTO>> responseEntity = patronController.getAllPatrons();

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody().size());
    }
}
