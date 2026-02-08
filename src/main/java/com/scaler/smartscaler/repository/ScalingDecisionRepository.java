package com.scaler.smartscaler.repository;

import com.scaler.smartscaler.entity.ScalingDecision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScalingDecisionRepository extends JpaRepository<ScalingDecision, Long> {
    List<ScalingDecision> findByServerId(String serverId);
    List<ScalingDecision> findByExecuted(Boolean executed);
}