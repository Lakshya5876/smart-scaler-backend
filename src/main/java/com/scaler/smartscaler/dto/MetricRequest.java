package com.scaler.smartscaler.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetricRequest {
    
    @NotBlank(message = "Server ID is required")
    private String serverId;
    
    @NotNull(message = "CPU usage is required")
    @Min(value = 0, message = "CPU usage must be between 0 and 100")
    @Max(value = 100, message = "CPU usage must be between 0 and 100")
    private Double cpuUsage;
    
    @NotNull(message = "Memory usage is required")
    @Min(value = 0, message = "Memory usage must be between 0 and 100")
    @Max(value = 100, message = "Memory usage must be between 0 and 100")
    private Double memoryUsage;
    
    @Min(value = 0, message = "Disk usage must be between 0 and 100")
    @Max(value = 100, message = "Disk usage must be between 0 and 100")
    private Double diskUsage;
}