package com.smartsafetynetwork.api.repository.vulnerableRegin;

import com.smartsafetynetwork.api.common.DetailId;
import com.smartsafetynetwork.api.domain.VulnerableRegin;
import com.smartsafetynetwork.api.dto.vulnerableRegin.response.PageDto;
import com.smartsafetynetwork.api.dto.vulnerableRegin.response.VRDetailResponseDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VulnerableReginRepository extends JpaRepository<VulnerableRegin, String> {
    @Query("select new com.smartsafetynetwork.api.dto.vulnerableRegin.response.PageDto"
            + "(vr.id, vr.title, vr.content, u.name, vr.createAt) from VulnerableRegin vr join vr.user u")
    List<PageDto> findAllPost();

    @Query("select new com.smartsafetynetwork.api.dto.vulnerableRegin.response.PageDto(vr.id, vr.title, vr.content, u.name, vr.createAt) " +
            "from VulnerableRegin vr join vr.user u " +
            "where vr.user.id = :userId")
    List<PageDto> findUserPost(@Param("userId") String userId);

    @Query("select new com.smartsafetynetwork.api.dto.vulnerableRegin.response.VRDetailResponseDto(" +
            "vr.id, vr.title, vr.content, vr.createAt, vr.address, vr.latitude, vr.longitude, u.name) " +
            "from VulnerableRegin vr join vr.user u " +
            "where vr.id = :detailId")
    VRDetailResponseDto findMyPost(@Param("detailId") String detailId);
}
