package com.smartsafetynetwork.api.vulnerableRegion.repository;

import com.smartsafetynetwork.api.vulnerableRegion.model.VulnerableRegin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VulnerableReginRepository extends JpaRepository<VulnerableRegin, String> {
}
