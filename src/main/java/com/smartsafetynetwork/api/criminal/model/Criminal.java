package com.smartsafetynetwork.api.criminal.model;

import com.smartsafetynetwork.api.common.model.BaseEntity;
import com.smartsafetynetwork.api.criminalBoard.model.CriminalBoard;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

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

    @OneToMany(mappedBy = "criminal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CriminalBoard> criminalBoards = new ArrayList<>();

    public void modify(String name, String registration_place, String address, String crime) {
        this.name = name;
        this.registration_place = registration_place;
        this.address = address;
        this.crime = crime;
    }
}
