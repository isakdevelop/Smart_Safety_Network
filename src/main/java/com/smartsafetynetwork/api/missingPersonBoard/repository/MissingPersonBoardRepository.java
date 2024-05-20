package com.smartsafetynetwork.api.missingPersonBoard.repository;

import com.smartsafetynetwork.api.missingPersonBoard.model.MissingPersonBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissingPersonBoardRepository extends JpaRepository<MissingPersonBoard, String> {
}
