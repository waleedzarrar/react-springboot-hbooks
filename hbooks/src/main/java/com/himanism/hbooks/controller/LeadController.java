package com.himanism.hbooks.controller;

import com.himanism.hbooks.dto.request.LeadRequestDTO;
import com.himanism.hbooks.dto.response.LeadResponseDTO;
import com.himanism.hbooks.service.LeadService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leads")
@Slf4j
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @PostMapping
    public ResponseEntity<LeadResponseDTO> createLead(@Valid @RequestBody LeadRequestDTO leadRequestDTO) {
        log.info("Received request to create lead");
        LeadResponseDTO leadResponseDTO = leadService.createLead(leadRequestDTO);
        return ResponseEntity.ok(leadResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeadResponseDTO> getLeadById(@PathVariable Long id) {
        log.info("Received request to get lead with id: {}", id);
        LeadResponseDTO leadResponseDTO = leadService.getLeadById(id);
        return ResponseEntity.ok(leadResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<LeadResponseDTO>> getAllLeads() {
        log.info("Received request to get all leads");
        List<LeadResponseDTO> leads = leadService.getAllLeads();
        return ResponseEntity.ok(leads);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeadResponseDTO> updateLead(@PathVariable Long id, @Valid @RequestBody LeadRequestDTO leadRequestDTO) {
        log.info("Received request to update lead with id: {}", id);
        LeadResponseDTO updatedLead = leadService.updateLead(id, leadRequestDTO);
        return ResponseEntity.ok(updatedLead);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLead(@PathVariable Long id) {
        log.info("Received request to delete lead with id: {}", id);
        leadService.deleteLeadById(id);
        return ResponseEntity.noContent().build();
    }
}
