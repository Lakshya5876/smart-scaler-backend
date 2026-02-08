package com.scaler.smartscaler.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "scaling_decisions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScalingDecision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String serverId;
    
    @Column(nullable = false)
    private String action; // SCALE_UP, SCALE_DOWN, NO_ACTION
    
    @Column(nullable = false)
    private String reason;
    
    private Integer currentInstances;
    private Integer recommendedInstances;
    
    @Column(nullable = false)
    private LocalDateTime decidedAt;
    
    @Column(nullable = false)
    private Boolean executed = false;
}