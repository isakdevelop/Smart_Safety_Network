package com.smartsafetynetwork.api.auth.repository;

import com.smartsafetynetwork.api.auth.model.Certification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends CrudRepository<Certification, String>  {
}
