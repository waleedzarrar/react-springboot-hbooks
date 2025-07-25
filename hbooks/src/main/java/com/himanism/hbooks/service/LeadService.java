package com.himanism.hbooks.service;

import com.himanism.hbooks.dto.request.LeadRequestDTO;
import com.himanism.hbooks.dto.response.LeadResponseDTO;

import java.util.List;

public interface LeadService {

    LeadResponseDTO createLead(LeadRequestDTO leadRequestDTO);

    LeadResponseDTO getLeadById(Long id);

    List<LeadResponseDTO> getAllLeads();

    LeadResponseDTO updateLead(Long id, LeadRequestDTO leadRequestDTO);

    void deleteLeadById(Long id);
}