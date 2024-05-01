package com.smartsafetynetwork.api.auth.repository;

import com.smartsafetynetwork.api.auth.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, String> {
    boolean existsByCertificationNumber(String password);
}
