package com.smartsafetynetwork.api.repository.missingPersonBoard;

import com.smartsafetynetwork.api.common.DetailId;
import com.smartsafetynetwork.api.domain.MissingPersonBoard;
import com.smartsafetynetwork.api.dto.missingPersonBoard.response.MPBDetailResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MissingPersonBoardRepository extends JpaRepository<MissingPersonBoard, String> {
    @Query("select new com.smartsafetynetwork.api.dto.missingPersonBoard.response.MPBDetailResponseDto(" +
            "m.id, m.name")
    MPBDetailResponseDto detail(@Param("mbpId") String mbpId);
}
