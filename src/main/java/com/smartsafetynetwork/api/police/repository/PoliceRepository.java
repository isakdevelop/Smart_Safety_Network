package com.smartsafetynetwork.api.police.repository;

import com.smartsafetynetwork.api.police.model.Police;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliceRepository extends JpaRepository<Police, String> {
    boolean existsByPoliceNumber(String policeNumber);

    Optional<Police> findByUser_Id(String userId);
}
