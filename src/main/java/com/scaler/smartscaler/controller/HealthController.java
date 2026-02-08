package com.scaler.smartscaler.controller;

import com.scaler.smartscaler.entity.HealthStatus;
import com.scaler.smartscaler.service.HealthAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/health")
public class HealthController {
    
    @Autowired
    private HealthAnalysisService healthAnalysisService;
    
    @PostMapping("/analyze/{serverId}")
    public ResponseEntity<HealthStatus> analyzeHealth(@PathVariable String serverId) {
        HealthStatus health = healthAnalysisService.analyzeServerHealth(serverId);
        if (health == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(health);
    }
    
    @GetMapping
    public ResponseEntity<List<HealthStatus>> getAllHealthStatuses() {
        return ResponseEntity.ok(healthAnalysisService.getAllHealthStatuses());
    }
    
    @GetMapping("/{serverId}")
    public ResponseEntity<HealthStatus> getLatestHealth(@PathVariable String serverId) {
        HealthStatus health = healthAnalysisService.getLatestHealthStatus(serverId);
        if (health == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(health);
    }
}