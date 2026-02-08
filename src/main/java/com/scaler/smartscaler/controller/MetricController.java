package com.scaler.smartscaler.controller;

import com.scaler.smartscaler.dto.MetricRequest;
import com.scaler.smartscaler.entity.Metric;
import com.scaler.smartscaler.service.MetricService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/metrics")
public class MetricController {
    
    @Autowired
    private MetricService metricService;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createMetric(@Valid @RequestBody MetricRequest request) {
        Metric savedMetric = metricService.createMetric(request);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Metric recorded successfully");
        response.put("metricId", savedMetric.getId());
        response.put("status", savedMetric.getStatus());
        response.put("timestamp", savedMetric.getTimestamp());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<List<Metric>> getAllMetrics() {
        return ResponseEntity.ok(metricService.getAllMetrics());
    }
    
    @GetMapping("/latest")
    public ResponseEntity<Metric> getLatestMetric() {
        Metric latest = metricService.getLatestMetric();
        if (latest == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(latest);
    }
    
    @GetMapping("/server/{serverId}")
    public ResponseEntity<List<Metric>> getMetricsByServer(@PathVariable String serverId) {
        return ResponseEntity.ok(metricService.getMetricsByServer(serverId));
    }
}