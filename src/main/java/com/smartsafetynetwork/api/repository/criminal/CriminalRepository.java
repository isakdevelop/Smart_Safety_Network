package com.smartsafetynetwork.api.repository.criminal;

import com.smartsafetynetwork.api.domain.Criminal;
import com.smartsafetynetwork.api.dto.criminal.response.CriminalDetailResponseDto;
import com.smartsafetynetwork.api.dto.criminal.response.CriminalListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CriminalRepository extends JpaRepository<Criminal, String> {
    @Query("select new com.smartsafetynetwork.api.dto.criminal.response.CriminalListResponseDto(" +
            "c.id, c.name, c.age, c.crime, c.imagePath) " +
            "from Criminal c")
    Page<CriminalListResponseDto> findAllList(Pageable pageable);

    @Query("select new com.smartsafetynetwork.api.dto.criminal.response.CriminalListResponseDto(" +
            "c.id, c.name, c.age, c.crime, c.imagePath) " +
            "from Criminal c " +
            "where c.name = :name")
    Page<CriminalListResponseDto> findByNameList(Pageable pageable, @Param("name") String name);

    @Query("select new com.smartsafetynetwork.api.dto.criminal.response.CriminalListResponseDto(" +
            "c.id, c.name, c.age, c.crime, c.imagePath) " +
            "from Criminal c " +
            "where c.crime like :guilty")
    Page<CriminalListResponseDto> findByGuiltyList(Pageable pageable, String guilty);

    @Query("select new com.smartsafetynetwork.api.dto.criminal.response.CriminalDetailResponseDto("
            + "c.id, c.name, c.crime, c.registration_place, c.address, c.imagePath) "
            + "from Criminal c "
            + "where c.id = :criminalId")
    CriminalDetailResponseDto findDetailList(@Param("criminalId") String criminalId);

}
