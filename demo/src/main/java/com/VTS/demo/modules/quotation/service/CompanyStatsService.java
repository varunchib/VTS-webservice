package com.VTS.demo.modules.quotation.service;

import com.VTS.demo.modules.quotation.config.StoredProcClient;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompanyStatsService {

    private final StoredProcClient procClient;

    public CompanyStatsService(StoredProcClient procClient) {
        this.procClient = procClient;
    }

    private UUID getUserIdOrNull(boolean scopedToUser) {
        if (!scopedToUser) return null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) return UUID.fromString(auth.getName());
        throw new RuntimeException("Unauthorized: cannot extract user id");
    }

    /** Flexible: if scopedToUser = true, filters by logged-in user; else returns ALL */
    public JsonNode getCompanyStats(boolean scopedToUser) {
        UUID createdBy = getUserIdOrNull(scopedToUser); // null => ALL
        return procClient.callCompanyStats("prr_get_company_stats", createdBy);
    }
}