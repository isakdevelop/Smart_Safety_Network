package com.smartsafetynetwork.api.criminal.repository;

import com.smartsafetynetwork.api.criminal.model.Criminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriminalRepository extends JpaRepository<Criminal, String> {
}
