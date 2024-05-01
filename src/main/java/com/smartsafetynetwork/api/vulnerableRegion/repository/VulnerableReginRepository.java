package com.smartsafetynetwork.api.vulnerableRegion.repository;

import com.smartsafetynetwork.api.vulnerableRegion.model.VulnerableRegin;
import com.smartsafetynetwork.api.vulnerableRegion.dto.response.VRDetailResponseDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.response.VRListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VulnerableReginRepository extends JpaRepository<VulnerableRegin, String> {
    @Query("select new com.smartsafetynetwork.api.vulnerableRegion.dto.response.VRListResponseDto(" +
            "vr.id, vr.title, vr.content, u.name) from VulnerableRegin vr join vr.user u")
    Page<VRListResponseDto> findAllPost(Pageable pageable);

    @Query("select new com.smartsafetynetwork.api.vulnerableRegion.dto.response.VRListResponseDto(" +
            "vr.id, vr.title, vr.content, u.name) " +
            "from VulnerableRegin vr join vr.user u " +
            "where vr.user.id = :userId")
    Page<VRListResponseDto> findUserPost(Pageable pageable, @Param("userId") String userId);

    @Query("select new com.smartsafetynetwork.api.vulnerableRegion.dto.response.VRDetailResponseDto(" +
            "vr.id, vr.title, vr.content, vr.createAt, vr.address, vr.latitude, vr.longitude, u.name) " +
            "from VulnerableRegin vr join vr.user u " +
            "where vr.id = :vrId")
    VRDetailResponseDto findUserDetailPost(@Param("vrId") String vrId);

    @Query("select new com.smartsafetynetwork.api.vulnerableRegion.dto.response.VRListResponseDto(" +
            "vr.id, vr.title, vr.content, u.name) " +
            "from VulnerableRegin vr join vr.user u " +
            "where vr.address like %:region% and u.id = :userId")
    Page<VRListResponseDto> findUserPostByRegion(Pageable pageable, @Param("region") String region, @Param("userId") String userId);


    @Query("select new com.smartsafetynetwork.api.vulnerableRegion.dto.response.VRListResponseDto(" +
            "vr.id, vr.title, vr.content, u.name) " +
            "from VulnerableRegin vr join vr.user u " +
            "where vr.address like %:region%")
    Page<VRListResponseDto> findAllPostByRegion(Pageable pageable, @Param("region") String region);
}
