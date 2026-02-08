package com.scaler.smartscaler.service;

import com.scaler.smartscaler.entity.HealthStatus;
import com.scaler.smartscaler.entity.ScalingDecision;
import com.scaler.smartscaler.repository.HealthStatusRepository;
import com.scaler.smartscaler.repository.ScalingDecisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScalingService {
    
    @Autowired
    private HealthStatusRepository healthStatusRepository;
    
    @Autowired
    private ScalingDecisionRepository scalingDecisionRepository;
    
    public ScalingDecision makeScalingDecision(String serverId) {
        HealthStatus latestHealth = healthStatusRepository.findFirstByServerIdOrderByAnalyzedAtDesc(serverId);
        
        if (latestHealth == null) {
            return null;
        }
        
        String action;
        String reason;
        Integer currentInstances = 3; // Simulated
        Integer recommendedInstances = currentInstances;
        
        double avgUsage = (latestHealth.getAvgCpuUsage() + latestHealth.getAvgMemoryUsage()) / 2;
        
        if (avgUsage >= 80) {
            action = "SCALE_UP";
            recommendedInstances = currentInstances + 2;
            reason = String.format("High resource usage detected (%.2f%%). Scaling up from %d to %d instances.", 
                avgUsage, currentInstances, recommendedInstances);
        } else if (avgUsage < 30) {
            action = "SCALE_DOWN";
            recommendedInstances = Math.max(1, currentInstances - 1);
            reason = String.format("Low resource usage detected (%.2f%%). Scaling down from %d to %d instances.", 
                avgUsage, currentInstances, recommendedInstances);
        } else {
            action = "NO_ACTION";
            reason = String.format("Resource usage normal (%.2f%%). No scaling needed.", avgUsage);
        }
        
        ScalingDecision decision = new ScalingDecision();
        decision.setServerId(serverId);
        decision.setAction(action);
        decision.setReason(reason);
        decision.setCurrentInstances(currentInstances);
        decision.setRecommendedInstances(recommendedInstances);
        decision.setDecidedAt(LocalDateTime.now());
        
        return scalingDecisionRepository.save(decision);
    }
    
    public List<ScalingDecision> getAllDecisions() {
        return scalingDecisionRepository.findAll();
    }
    
    public List<ScalingDecision> getPendingDecisions() {
        return scalingDecisionRepository.findByExecuted(false);
    }
}