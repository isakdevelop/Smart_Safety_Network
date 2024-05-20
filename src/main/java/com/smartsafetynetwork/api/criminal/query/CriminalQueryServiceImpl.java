package com.smartsafetynetwork.api.criminal.query;

import static org.hibernate.engine.config.spi.StandardConverters.asString;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.criminal.dto.CriminalDetailDto;
import com.smartsafetynetwork.api.criminal.dto.CriminalListDto;
import com.smartsafetynetwork.api.criminal.dto.QCriminalDetailDto;
import com.smartsafetynetwork.api.criminal.dto.QCriminalListDto;
import com.smartsafetynetwork.api.criminal.model.QCriminal;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CriminalQueryServiceImpl implements CriminalQueryService {
    private final JPAQueryFactory jpaQueryFactory;
    private final QCriminal criminal = QCriminal.criminal;
    private Long totalCount;

    @PostConstruct
    public void init() {
        totalCount = jpaQueryFactory
                .select(criminal.count())
                .from(criminal)
                .fetchOne();
    }

    public Page<CriminalListDto> list(Pageable pageable) {
        List<CriminalListDto> list =  jpaQueryFactory
                .select(
                        new QCriminalListDto(
                                criminal.id,
                                criminal.name,
                                criminal.crime,
                                criminal.imagePath
                        )
                )
                .from(criminal)
                .orderBy(criminal.createAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(list, pageable, totalCount);
    }


    public Page<CriminalListDto> listByName(Pageable pageable, String name) {
        List<CriminalListDto> list = jpaQueryFactory
                .select(
                        new QCriminalListDto(
                                criminal.id,
                                criminal.name,
                                criminal.crime,
                                criminal.imagePath)
                )
                .from(criminal)
                .where(criminal.name.eq(name))
                .orderBy(criminal.createAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(list, pageable, totalCount);
    }

    public Page<CriminalListDto> listByGuilty (Pageable pageable, String guilty) {
        List<CriminalListDto> list = jpaQueryFactory
                .select(new QCriminalListDto(
                                criminal.id,
                                criminal.name,
                                criminal.crime,
                                criminal.imagePath)
                )
                .from(criminal)
                .where(criminal.crime.like(asString("%").concat(guilty).concat("%")))
                .orderBy(criminal.createAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(list, pageable, totalCount);
    }

    public CriminalDetailDto detail (RequestId requestId) {
        return jpaQueryFactory
                .select(new QCriminalDetailDto(
                                criminal.id,
                                criminal.name,
                                criminal.age,
                                criminal.crime,
                                criminal.registrationPlace,
                                criminal.address,
                                criminal.imagePath)
                )
                .from(criminal)
                .where(criminal.id.eq(requestId.getId()))
                .fetchOne();
    }
}
