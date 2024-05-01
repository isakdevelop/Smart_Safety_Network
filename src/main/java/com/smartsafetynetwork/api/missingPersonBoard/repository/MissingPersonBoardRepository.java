package com.smartsafetynetwork.api.missingPersonBoard.repository;

import com.smartsafetynetwork.api.missingPersonBoard.model.MissingPersonBoard;
import com.smartsafetynetwork.api.missingPersonBoard.dto.response.MPBDetailResponseDto;
import com.smartsafetynetwork.api.missingPersonBoard.dto.response.MPBListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MissingPersonBoardRepository extends JpaRepository<MissingPersonBoard, String> {
    @Query("select new com.smartsafetynetwork.api.missingPersonBoard.dto.response.MPBListResponseDto(" +
            "m.id, m.title, m.content, u.name) " +
            "from MissingPersonBoard m join m.user u " +
            "where u.id = :userId"
    )
    Page<MPBListResponseDto> findUserList(Pageable pageable, @Param("userId") String userId);

    @Query("select new com.smartsafetynetwork.api.missingPersonBoard.dto.response.MPBListResponseDto(" +
            "m.id, m.title, m.content, u.name) " +
            "from MissingPersonBoard m join m.user u"
    )
    Page<MPBListResponseDto> findAllList(Pageable pageable);
    @Query("select new com.smartsafetynetwork.api.missingPersonBoard.dto.response.MPBDetailResponseDto(" +
            "m.id, m.title, m.content, m.address, m.latitude, m.longitude, u.name, p.name) " +
            "from MissingPersonBoard m " +
            "join m.user u " +
            "join m.missingPerson p " +
            "where m.id = :mbpId")
    MPBDetailResponseDto detail(@Param("mbpId") String mbpId);
}
