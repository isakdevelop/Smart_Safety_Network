package com.smartsafetynetwork.api.repository.missingPersonBoard;

import com.smartsafetynetwork.api.domain.MissingPersonBoard;
import com.smartsafetynetwork.api.dto.missingPersonBoard.response.MPBDetailResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MissingPersonBoardRepository extends JpaRepository<MissingPersonBoard, String> {
    @Query("select new com.smartsafetynetwork.api.dto.missingPersonBoard.response.MPBDetailResponseDto(" +
            "m.id, m.title, m.content, m.address, m.latitude, m.longitude, u.name, p.name) " +
            "from MissingPersonBoard m " +
            "join m.user u " +
            "join m.missingPerson p " +
            "where m.id = :mbpId")
    MPBDetailResponseDto detail(@Param("mbpId") String mbpId);
}
