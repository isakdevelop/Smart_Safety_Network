package com.smartsafetynetwork.api.repository.admin;

import com.smartsafetynetwork.api.domain.Admin;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    Optional<Admin> findByPoliceNumber(String policeNumber);
}
