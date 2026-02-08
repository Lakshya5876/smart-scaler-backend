package com.scaler.smartscaler.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "health_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String serverId;
    
    @Column(nullable = false)
    private String status; // HEALTHY, DEGRADED, CRITICAL
    
    private Double avgCpuUsage;
    private Double avgMemoryUsage;
    
    @Column(nullable = false)
    private LocalDateTime analyzedAt;
    
    private String recommendation;
}