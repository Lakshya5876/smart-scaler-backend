package com.scaler.smartscaler.repository;

import com.scaler.smartscaler.entity.HealthStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HealthStatusRepository extends JpaRepository<HealthStatus, Long> {
    List<HealthStatus> findByServerId(String serverId);
    HealthStatus findFirstByServerIdOrderByAnalyzedAtDesc(String serverId);
}