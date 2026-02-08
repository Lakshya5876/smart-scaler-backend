package com.scaler.smartscaler.service;

import com.scaler.smartscaler.entity.Alert;
import com.scaler.smartscaler.entity.HealthStatus;
import com.scaler.smartscaler.entity.Metric;
import com.scaler.smartscaler.repository.AlertRepository;
import com.scaler.smartscaler.repository.HealthStatusRepository;
import com.scaler.smartscaler.repository.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HealthAnalysisService {
    
    @Autowired
    private MetricRepository metricRepository;
    
    @Autowired
    private HealthStatusRepository healthStatusRepository;
    
    @Autowired
    private AlertRepository alertRepository;
    
    public HealthStatus analyzeServerHealth(String serverId) {
        List<Metric> recentMetrics = metricRepository.findByServerId(serverId);
        
        if (recentMetrics.isEmpty()) {
            return null;
        }
        
        // Calculate averages from last 5 metrics
        int limit = Math.min(5, recentMetrics.size());
        double avgCpu = recentMetrics.stream().limit(limit).mapToDouble(Metric::getCpuUsage).average().orElse(0);
        double avgMemory = recentMetrics.stream().limit(limit).mapToDouble(Metric::getMemoryUsage).average().orElse(0);
        double avgOverall = (avgCpu + avgMemory) / 2;
        
        String status;
        String recommendation;
        String alertSeverity = null;
        
        if (avgOverall >= 80) {
            status = "CRITICAL";
            recommendation = "Immediate scaling required. System overloaded.";
            alertSeverity = "CRITICAL";
        } else if (avgOverall >= 60) {
            status = "DEGRADED";
            recommendation = "Consider scaling up. Performance may be impacted.";
            alertSeverity = "WARNING";
        } else {
            status = "HEALTHY";
            recommendation = "System operating normally.";
        }
        
        // Save health status
        HealthStatus healthStatus = new HealthStatus();
        healthStatus.setServerId(serverId);
        healthStatus.setStatus(status);
        healthStatus.setAvgCpuUsage(avgCpu);
        healthStatus.setAvgMemoryUsage(avgMemory);
        healthStatus.setAnalyzedAt(LocalDateTime.now());
        healthStatus.setRecommendation(recommendation);
        
        healthStatusRepository.save(healthStatus);
        
        // Create alert if needed
        if (alertSeverity != null) {
            Alert alert = new Alert();
            alert.setServerId(serverId);
            alert.setSeverity(alertSeverity);
            alert.setMessage(String.format("Server %s is %s. Avg CPU: %.2f%%, Avg Memory: %.2f%%", 
                serverId, status, avgCpu, avgMemory));
            alert.setCreatedAt(LocalDateTime.now());
            alertRepository.save(alert);
        }
        
        return healthStatus;
    }
    
    public List<HealthStatus> getAllHealthStatuses() {
        return healthStatusRepository.findAll();
    }
    
    public HealthStatus getLatestHealthStatus(String serverId) {
        return healthStatusRepository.findFirstByServerIdOrderByAnalyzedAtDesc(serverId);
    }
}