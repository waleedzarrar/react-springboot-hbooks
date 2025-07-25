package com.himanism.hbooks.service.impl;

import com.himanism.hbooks.dto.request.LeadRequestDTO;
import com.himanism.hbooks.dto.response.LeadResponseDTO;
import com.himanism.hbooks.entity.Lead;
import com.himanism.hbooks.entity.User; // Import User entity
import com.himanism.hbooks.exception.LeadNotFoundException;
import com.himanism.hbooks.exception.UserNotFoundException; // Import UserNotFoundException (you might need to create this)
import com.himanism.hbooks.mapper.LeadMapper;
import com.himanism.hbooks.repository.LeadRepository;
import com.himanism.hbooks.repository.UserRepository; // Import UserRepository
import com.himanism.hbooks.service.LeadService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class LeadServiceImpl implements LeadService {

    private final LeadRepository leadRepository;
    private final LeadMapper leadMapper;
    private final UserRepository userRepository; // Inject UserRepository

    public LeadServiceImpl(LeadRepository leadRepository, LeadMapper leadMapper, UserRepository userRepository) {
        this.leadRepository = leadRepository;
        this.leadMapper = leadMapper;
        this.userRepository = userRepository; // Initialize UserRepository
    }

    @Override
    @Transactional
    public LeadResponseDTO createLead(LeadRequestDTO leadRequestDTO) {
        try {
            // Fetch the User entity based on userId from DTO
            User user = userRepository.findById(leadRequestDTO.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("User not found with id: " + leadRequestDTO.getUserId()));

            Lead lead = leadMapper.toEntity(leadRequestDTO);
            lead.setUser(user); // Set the fetched User object to the Lead entity

            Lead savedLead = leadRepository.save(lead);
            log.info("Lead created with id: {}", savedLead.getId());
            return leadMapper.toDto(savedLead);
        } catch (Exception e) {
            log.error("Error creating lead", e);
            throw e;
        }
    }

    @Override
    public LeadResponseDTO getLeadById(Long id) {
        try {
            Lead lead = leadRepository.findById(id)
                    .orElseThrow(() -> new LeadNotFoundException("Lead not found with id: " + id));
            return leadMapper.toDto(lead);
        } catch (Exception e) {
            log.error("Error fetching lead by id: {}", id, e);
            throw e;
        }
    }

    @Override
    public List<LeadResponseDTO> getAllLeads() {
        try {
            List<Lead> leads = leadRepository.findAll();
            return leadMapper.toDtoList(leads);
        } catch (Exception e) {
            log.error("Error fetching all leads", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public LeadResponseDTO updateLead(Long id, LeadRequestDTO leadRequestDTO) {
        try {
            Lead existingLead = leadRepository.findById(id)
                    .orElseThrow(() -> new LeadNotFoundException("Lead not found with id: " + id));

            // Fetch the User entity based on userId from DTO for update
            User user = userRepository.findById(leadRequestDTO.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("User not found with id: " + leadRequestDTO.getUserId()));

            leadMapper.updateLeadFromDto(leadRequestDTO, existingLead);
            existingLead.setUser(user); // Set the fetched User object to the existing Lead entity

            Lead updatedLead = leadRepository.save(existingLead);
            log.info("Lead updated with id: {}", updatedLead.getId());
            return leadMapper.toDto(updatedLead);
        } catch (Exception e) {
            log.error("Error updating lead id: {}", id, e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteLeadById(Long id) {
        try {
            if (!leadRepository.existsById(id)) {
                throw new LeadNotFoundException("Lead not found with id: " + id);
            }
            leadRepository.deleteById(id);
            log.info("Lead deleted with id: {}", id);
        } catch (Exception e) {
            log.error("Error deleting lead with id: {}", id, e);
            throw e;
        }
    }
}
