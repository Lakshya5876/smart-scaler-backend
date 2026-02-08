package com.scaler.smartscaler.repository;

import com.scaler.smartscaler.entity.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetricRepository extends JpaRepository<Metric, Long> {
    
    // Custom query methods
    List<Metric> findByServerId(String serverId);
    
    Metric findFirstByOrderByTimestampDesc();
}