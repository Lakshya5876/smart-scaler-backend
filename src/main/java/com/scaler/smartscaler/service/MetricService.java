package com.scaler.smartscaler.service;

import com.scaler.smartscaler.dto.MetricRequest;
import com.scaler.smartscaler.entity.Metric;
import com.scaler.smartscaler.repository.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MetricService {
    
    @Autowired
    private MetricRepository metricRepository;
    
    public Metric createMetric(MetricRequest request) {
        String status = determineStatus(request.getCpuUsage(), request.getMemoryUsage());
        
        Metric metric = new Metric();
        metric.setServerId(request.getServerId());
        metric.setCpuUsage(request.getCpuUsage());
        metric.setMemoryUsage(request.getMemoryUsage());
        metric.setDiskUsage(request.getDiskUsage());
        metric.setTimestamp(LocalDateTime.now());
        metric.setStatus(status);
        
        return metricRepository.save(metric);
    }
    
    public List<Metric> getAllMetrics() {
        return metricRepository.findAll();
    }
    
    public Metric getLatestMetric() {
        return metricRepository.findFirstByOrderByTimestampDesc();
    }
    
    public List<Metric> getMetricsByServer(String serverId) {
        return metricRepository.findByServerId(serverId);
    }
    
    private String determineStatus(Double cpuUsage, Double memoryUsage) {
        double avgUsage = (cpuUsage + memoryUsage) / 2;
        if (avgUsage >= 80) return "CRITICAL";
        else if (avgUsage >= 60) return "DEGRADED";
        else return "HEALTHY";
    }
}