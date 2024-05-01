package com.smartsafetynetwork.api.criminalBoard.repository;

import com.smartsafetynetwork.api.criminalBoard.model.CriminalBoard;
import com.smartsafetynetwork.api.criminalBoard.dto.response.CBResponseDto;
import com.smartsafetynetwork.api.criminalBoard.dto.response.CriminalBoardListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CriminalBoardRepository extends JpaRepository<CriminalBoard, String> {
    @Query("select new com.smartsafetynetwork.api.criminalBoard.dto.response.CriminalBoardListDto(" +
            "cb.id, cb.title, cb.content, u.name, c.name) " +
            "from CriminalBoard cb join cb.user u join cb.criminal c " +
            "where u.id = :userId")
    Page<CriminalBoardListDto> findUserPost(Pageable pageable, @Param("userId") String userId);

    @Query("select new com.smartsafetynetwork.api.criminalBoard.dto.response.CriminalBoardListDto(" +
            "cb.id, cb.title, cb.content, u.name, c.name) " +
            "from CriminalBoard cb join cb.user u join cb.criminal c")
    Page<CriminalBoardListDto> findAllPost(Pageable pageable);

    @Query("select new com.smartsafetynetwork.api.criminalBoard.dto.response.CriminalBoardListDto(" +
            "cb.id, cb.title, cb.content, u.name, c.name) " +
            "from CriminalBoard cb join cb.user u join cb.criminal c " +
            "where u.name = :name")
    Page<CriminalBoardListDto> findPostByName(Pageable pageable, @Param("name") String name);

    @Query("select new com.smartsafetynetwork.api.criminalBoard.dto.response.CBResponseDto(" +
            "cb.id, cb.title, cb.content, cb.address, cb.latitude, cb.longitude, u.name, c.name) " +
            "from CriminalBoard cb join cb.user u join cb.criminal c " +
            "where cb.id = :cbId")
    CBResponseDto detail(@Param("cbId") String cbId);
}
