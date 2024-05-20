package com.smartsafetynetwork.api.criminalBoard.repository;

import com.smartsafetynetwork.api.criminalBoard.model.CriminalBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriminalBoardRepository extends JpaRepository<CriminalBoard, String> {
}
