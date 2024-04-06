package com.smartsafetynetwork.api.repository.certification;

import com.smartsafetynetwork.api.domain.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, String> {
    boolean existsByCertificationNumber(String password);
}
