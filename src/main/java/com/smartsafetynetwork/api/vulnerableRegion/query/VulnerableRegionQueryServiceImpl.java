package com.smartsafetynetwork.api.vulnerableRegion.query;

import static org.hibernate.engine.config.spi.StandardConverters.asString;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.vulnerableRegion.dto.QVRDetailDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.QVRListDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.VRDetailDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.VRListDto;
import com.smartsafetynetwork.api.vulnerableRegion.model.QVulnerableRegin;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VulnerableRegionQueryServiceImpl implements VulnerableReginQueryService {
    private final JPAQueryFactory jpaQueryFactory;
    private final QVulnerableRegin vulnerableRegin = QVulnerableRegin.vulnerableRegin;
    private Long totalCount;

    @PostConstruct
    public void init() {
        totalCount = jpaQueryFactory
                .select(vulnerableRegin.count())
                .from(vulnerableRegin)
                .fetchOne();
    }

    @Override
    public Page<VRListDto> list(Pageable pageable) {
        List<VRListDto> list = jpaQueryFactory
                .select(
                        new QVRListDto(
                                vulnerableRegin.id,
                                vulnerableRegin.title,
                                vulnerableRegin.content,
                                vulnerableRegin.user.name
                        )
                )
                .from(vulnerableRegin)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(list, pageable, totalCount);
    }

    @Override
    public Page<VRListDto> listByRegion(Pageable pageable, String region) {
        List<VRListDto> list = jpaQueryFactory
                .select(
                        new QVRListDto(
                        vulnerableRegin.id,
                        vulnerableRegin.title,
                        vulnerableRegin.content,
                        vulnerableRegin.user.name
                        )
                )
                .from(vulnerableRegin)
                .where(vulnerableRegin.address.like(asString("%").concat(region).concat("%")))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(list, pageable, totalCount);
    }

    @Override
    public VRDetailDto detail(RequestId requestId) {
        return jpaQueryFactory
                .select(new QVRDetailDto(
                        vulnerableRegin.id,
                        vulnerableRegin.title,
                        vulnerableRegin.content,
                        vulnerableRegin.address,
                        vulnerableRegin.latitude,
                        vulnerableRegin.longitude,
                        vulnerableRegin.user.name)
                )
                .from(vulnerableRegin)
                .where(vulnerableRegin.id.eq(requestId.getId()))
                .fetchOne();
    }
}
