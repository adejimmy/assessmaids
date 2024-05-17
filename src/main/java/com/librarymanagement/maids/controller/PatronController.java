package com.librarymanagement.maids.controller;

import com.librarymanagement.maids.domain.RequestModel.PatronRequest;
import com.librarymanagement.maids.dto.PatronDTO;
import com.librarymanagement.maids.service.PatronService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    private static final Logger logger = LoggerFactory.getLogger(PatronController.class);

    @Autowired
    private PatronService patronService;



    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping("/patrons")
    public ResponseEntity<List<PatronDTO>> getAllPatrons() {
        List<PatronDTO> patronDtos = patronService.getAllPatrons();
        return ResponseEntity.ok(patronDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronDTO> getPatronById(@PathVariable Long id) {
        try {
            PatronDTO patron = patronService.getPatronById(id);
            return ResponseEntity.ok(patron);
        } catch (Exception e) {
            logger.error("Error retrieving patron with id: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<PatronDTO> createPatron(@RequestBody PatronRequest patronRequest) {
        try {
            PatronDTO createdPatron = patronService.createPatron(patronRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPatron);
        } catch (Exception e) {
            logger.error("Error creating patron", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatronDTO> updatePatron(@PathVariable Long id, @RequestBody PatronRequest patronRequest) {
        try {
            PatronDTO updatedPatron = patronService.updatePatron(id, patronRequest);
            return ResponseEntity.ok(updatedPatron);
        } catch (Exception e) {
            logger.error("Error updating patron with id: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        try {
            patronService.deletePatron(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error deleting patron with id: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
