package com.VTS.demo.modules.quotation.controller;

import com.VTS.demo.modules.quotation.service.CompanyStatsService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company-stats")
@CrossOrigin(origins = "${frontend.origin}", allowCredentials = "true")
public class CompanyStatsController {

    private final CompanyStatsService service;

    public CompanyStatsController(CompanyStatsService service) {
        this.service = service;
    }

    /** Get company stats for ALL data (no user filter). */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/all")
    public ResponseEntity<JsonNode> all() {
        return ResponseEntity.ok(service.getCompanyStats(false));
    }

    /** Get company stats scoped to the logged-in user. */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/me")
    public ResponseEntity<JsonNode> me() {
        return ResponseEntity.ok(service.getCompanyStats(true));
    }

    // --- POST aliases so POST works too (no body needed) ---

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/all")
    public ResponseEntity<JsonNode> allPost() {
        return all();
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/me")
    public ResponseEntity<JsonNode> mePost() {
        return me();
    }
}
