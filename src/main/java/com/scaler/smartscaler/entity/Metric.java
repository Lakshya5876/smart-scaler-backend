package com.scaler.smartscaler.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "metrics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Metric {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String serverId;
    
    @Column(nullable = false)
    private Double cpuUsage;
    
    @Column(nullable = false)
    private Double memoryUsage;
    
    private Double diskUsage;
    
    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    @Column(nullable = false)
    private String status;  // HEALTHY, DEGRADED, CRITICAL
}