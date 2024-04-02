package com.smartsafetynetwork.api.repository.missingPerson;

import com.smartsafetynetwork.api.domain.MissingPerson;
import com.smartsafetynetwork.api.dto.missingPerson.response.MissingPersonDetailResponseDto;
import com.smartsafetynetwork.api.dto.missingPerson.response.MissingPersonListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MissingPersonRepository extends JpaRepository<MissingPerson, String> {
    @Query("select new com.smartsafetynetwork.api.dto.missingPerson.response.MissingPersonListResponseDto(" +
            "m.id, m.name, m.age, m.gender, m.address, m.imagePath) " +
            "from MissingPerson m")
    Page<MissingPersonListResponseDto> findAllList(Pageable pageable);

    @Query("select new com.smartsafetynetwork.api.dto.missingPerson.response.MissingPersonDetailResponseDto("
            + "m.id, m.name, m.gender, m.age, m.location, m.date, m.latitude, m.longitude, m.address, "
            + "m.height, m.weight, m.physique, m.faceShape, m.hairColor, m.hairShape, m.cloth,m.imagePath) "
            + "from MissingPerson m "
            + "where m.id like :missingPersonId")
    MissingPersonDetailResponseDto findDetailList(@Param("missingPersonId") String missingPersonId);;
}
