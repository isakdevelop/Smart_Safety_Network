package com.smartsafetynetwork.api.missingPersonBoard.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.missingPersonBoard.dto.MPBDetailDto;
import com.smartsafetynetwork.api.missingPersonBoard.dto.MPBListDto;
import com.smartsafetynetwork.api.missingPersonBoard.dto.QMPBDetailDto;
import com.smartsafetynetwork.api.missingPersonBoard.dto.QMPBListDto;
import com.smartsafetynetwork.api.missingPersonBoard.model.QMissingPersonBoard;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MissingPersonBoardQueryServiceImpl implements MissingPersonBoardQueryService {
    private final JPAQueryFactory jpaQueryFactory;
    private final QMissingPersonBoard missingPersonBoard = QMissingPersonBoard.missingPersonBoard;

    private Long totalCount;

    @PostConstruct
    public void init() {
        totalCount = jpaQueryFactory
                .select(missingPersonBoard.count())
                .from(missingPersonBoard)
                .fetchOne();
    }

    @Override
    public Page<MPBListDto> list(Pageable pageable) {
        List<MPBListDto> list = jpaQueryFactory
                .select(
                        new QMPBListDto(
                                missingPersonBoard.id,
                                missingPersonBoard.title,
                                missingPersonBoard.content,
                                missingPersonBoard.user.name
                        )
                )
                .from(missingPersonBoard)
                .orderBy(missingPersonBoard.createAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(list, pageable, totalCount);
    }

    @Override
    public Page<MPBListDto> listByName(Pageable pageable, String name) {
        List<MPBListDto> list = jpaQueryFactory
                .select(
                        new QMPBListDto(
                                missingPersonBoard.id,
                                missingPersonBoard.title,
                                missingPersonBoard.content,
                                missingPersonBoard.user.name
                        )
                )
                .from(missingPersonBoard)
                .where(missingPersonBoard.missingPerson.name.eq(name))
                .orderBy(missingPersonBoard.createAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(list, pageable, totalCount);
    }

    @Override
    public MPBDetailDto detail(RequestId requestId) {
        return jpaQueryFactory
                .select(
                        new QMPBDetailDto(
                                missingPersonBoard.id,
                                missingPersonBoard.title,
                                missingPersonBoard.content,
                                missingPersonBoard.address,
                                missingPersonBoard.latitude,
                                missingPersonBoard.longitude,
                                missingPersonBoard.user.name,
                                missingPersonBoard.missingPerson.name
                        )
                )
                .from(missingPersonBoard)
                .where(missingPersonBoard.id.eq(requestId.getId()))
                .fetchOne();
    }
}
