package com.scaler.smartscaler.controller;

import com.scaler.smartscaler.entity.Alert;
import com.scaler.smartscaler.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {
    
    @Autowired
    private AlertRepository alertRepository;
    
    @GetMapping
    public ResponseEntity<List<Alert>> getAllAlerts() {
        return ResponseEntity.ok(alertRepository.findAll());
    }
    
    @GetMapping("/server/{serverId}")
    public ResponseEntity<List<Alert>> getAlertsByServer(@PathVariable String serverId) {
        return ResponseEntity.ok(alertRepository.findByServerId(serverId));
    }
    
    @GetMapping("/unacknowledged")
    public ResponseEntity<List<Alert>> getUnacknowledgedAlerts() {
        return ResponseEntity.ok(alertRepository.findByAcknowledged(false));
    }
    
    @PutMapping("/{id}/acknowledge")
    public ResponseEntity<Alert> acknowledgeAlert(@PathVariable Long id) {
        return alertRepository.findById(id)
            .map(alert -> {
                alert.setAcknowledged(true);
                return ResponseEntity.ok(alertRepository.save(alert));
            })
            .orElse(ResponseEntity.notFound().build());
    }
}