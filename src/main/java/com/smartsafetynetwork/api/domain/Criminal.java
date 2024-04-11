package com.smartsafetynetwork.api.domain;

import com.smartsafetynetwork.api.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Criminal extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "crime")
    private String crime;

    @Column(name = "age")
    private String age;

    @Column(name = "registration_place")
    private String registration_place;

    @Column(name = "address")
    private String address;

    @Column(name = "image_path")
    private String imagePath;

    @OneToMany(mappedBy = "criminal")
    private List<CriminalBoard> criminalBoards = new ArrayList<>();

    public void modify(String name, String registration_place, String address, String crime) {
        this.name = name;
        this.registration_place = registration_place;
        this.address = address;
        this.crime = crime;
    }
}
