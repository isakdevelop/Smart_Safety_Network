package com.smartsafetynetwork.api.criminalBoard.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.criminalBoard.dto.CriminalBoardDetailDto;
import com.smartsafetynetwork.api.criminalBoard.dto.QCriminalBoardDetailDto;
import com.smartsafetynetwork.api.criminalBoard.dto.CriminalBoardListDto;
import com.smartsafetynetwork.api.criminalBoard.dto.QCriminalBoardListDto;
import com.smartsafetynetwork.api.criminalBoard.model.QCriminalBoard;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CriminalBoardQueryServiceImpl implements CriminalBoardQueryService {
    private final JPAQueryFactory jpaQueryFactory;
    private final QCriminalBoard criminalBoard = QCriminalBoard.criminalBoard;
    private Long totalCount;

    @PostConstruct
    public void init() {
        totalCount = jpaQueryFactory
                .select(criminalBoard.count())
                .from(criminalBoard)
                .fetchOne();
    }

    @Override
    public Page<CriminalBoardListDto> list(Pageable pageable) {
        List<CriminalBoardListDto> list = jpaQueryFactory
                .select(
                        new QCriminalBoardListDto(
                                criminalBoard.id,
                                criminalBoard.title,
                                criminalBoard.content,
                                criminalBoard.user.name,
                                criminalBoard.criminal.crime
                        )
                )
                .from(criminalBoard)
                .orderBy(criminalBoard.createAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(list, pageable, totalCount);
    }

    @Override
    public Page<CriminalBoardListDto> listByName(Pageable pageable, String name) {
        List<CriminalBoardListDto> list = jpaQueryFactory
                .select(
                        new QCriminalBoardListDto(
                                criminalBoard.id,
                                criminalBoard.title,
                                criminalBoard.content,
                                criminalBoard.user.name,
                                criminalBoard.criminal.crime
                        )
                )
                .from(criminalBoard)
                .where(criminalBoard.criminal.name.eq(name))
                .orderBy(criminalBoard.createAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(list, pageable, totalCount);
    }

    @Override
    public CriminalBoardDetailDto detail(RequestId requestId) {
                return jpaQueryFactory
                .select(new QCriminalBoardDetailDto(
                        criminalBoard.id,
                        criminalBoard.title,
                        criminalBoard.content,
                        criminalBoard.address,
                        criminalBoard.latitude,
                        criminalBoard.longitude,
                        criminalBoard.user.name,
                        criminalBoard.criminal.crime)
                )
                .from(criminalBoard)
                .where(criminalBoard.id.eq(requestId.getId()))
                .fetchOne();
    }


}
