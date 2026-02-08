package com.scaler.smartscaler.controller;

import com.scaler.smartscaler.entity.ScalingDecision;
import com.scaler.smartscaler.service.ScalingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/scaling")
public class ScalingController {
    
    @Autowired
    private ScalingService scalingService;
    
    @PostMapping("/decide/{serverId}")
    public ResponseEntity<ScalingDecision> makeDecision(@PathVariable String serverId) {
        ScalingDecision decision = scalingService.makeScalingDecision(serverId);
        if (decision == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(decision);
    }
    
    @GetMapping
    public ResponseEntity<List<ScalingDecision>> getAllDecisions() {
        return ResponseEntity.ok(scalingService.getAllDecisions());
    }
    
    @GetMapping("/pending")
    public ResponseEntity<List<ScalingDecision>> getPendingDecisions() {
        return ResponseEntity.ok(scalingService.getPendingDecisions());
    }
}