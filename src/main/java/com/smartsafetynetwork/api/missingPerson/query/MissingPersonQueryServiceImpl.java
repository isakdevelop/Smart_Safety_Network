package com.smartsafetynetwork.api.missingPerson.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.missingPerson.dto.MissingPersonDetailDto;
import com.smartsafetynetwork.api.missingPerson.dto.MissingPersonListDto;
import com.smartsafetynetwork.api.missingPerson.dto.QMissingPersonDetailDto;
import com.smartsafetynetwork.api.missingPerson.dto.QMissingPersonListDto;
import com.smartsafetynetwork.api.missingPerson.model.QMissingPerson;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MissingPersonQueryServiceImpl implements MissingPersonQueryService {
    private final JPAQueryFactory jpaQueryFactory;
    private final QMissingPerson missingPerson = QMissingPerson.missingPerson;
    private Long totalCount;

    @PostConstruct
    public void init() {
        totalCount = jpaQueryFactory
                .select(missingPerson.count())
                .from(missingPerson)
                .fetchOne();
    }
    @Override
    public Page<MissingPersonListDto> list(Pageable pageable) {
        List<MissingPersonListDto> list = jpaQueryFactory
                .select(
                        new QMissingPersonListDto(
                               missingPerson.id,
                               missingPerson.name,
                               missingPerson.age,
                               missingPerson.gender,
                               missingPerson.address,
                               missingPerson.imagePath
                        )
                )
                .from(missingPerson)
                .orderBy(missingPerson.createAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(list, pageable, totalCount);
    }

    @Override
    public Page<MissingPersonListDto> listByName(Pageable pageable, String name) {
        List<MissingPersonListDto> list = jpaQueryFactory
                .select(
                        new QMissingPersonListDto(
                                missingPerson.id,
                                missingPerson.name,
                                missingPerson.age,
                                missingPerson.gender,
                                missingPerson.address,
                                missingPerson.imagePath
                        )
                )
                .from(missingPerson)
                .where(missingPerson.name.eq(name))
                .orderBy(missingPerson.createAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(list, pageable, totalCount);
    }

    @Override
    public MissingPersonDetailDto detail(RequestId requestId) {
        return jpaQueryFactory
                .select(new QMissingPersonDetailDto(
                        missingPerson.id,
                        missingPerson.name,
                        missingPerson.gender,
                        missingPerson.age,
                        missingPerson.location,
                        missingPerson.date,
                        missingPerson.latitude,
                        missingPerson.longitude,
                        missingPerson.address,
                        missingPerson.height,
                        missingPerson.weight,
                        missingPerson.physique,
                        missingPerson.faceShape,
                        missingPerson.hairColor,
                        missingPerson.hairShape,
                        missingPerson.cloth,
                        missingPerson.imagePath)
                        )
                .from(missingPerson)
                .where(missingPerson.id.eq(requestId.getId()))
                .fetchOne();
    }
}
