package com.smartsafetynetwork.api.missingPerson.repository;

import com.smartsafetynetwork.api.missingPerson.model.MissingPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissingPersonRepository extends JpaRepository<MissingPerson, String> {
}
