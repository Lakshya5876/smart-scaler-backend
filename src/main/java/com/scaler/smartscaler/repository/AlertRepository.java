package com.scaler.smartscaler.repository;

import com.scaler.smartscaler.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByServerId(String serverId);
    List<Alert> findBySeverity(String severity);
    List<Alert> findByAcknowledged(Boolean acknowledged);
}