package com.smartsafetynetwork.api.repository.missingPersonBoard;

import com.smartsafetynetwork.api.domain.MissingPersonBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissingPersonBoardRepository extends JpaRepository<MissingPersonBoard, String> {
}
