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
    private Integer age;

    @Column(name = "registrationPlace")
    private String registrationPlace;

    @Column(name = "address")
    private String address;

    @Column(name = "imagePath")
    private String imagePath;

    @OneToMany(mappedBy = "criminal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CriminalBoard> criminalBoards = new ArrayList<>();

    public void modify(String name, String registrationPlace, String address, String crime, String image) {
        this.name = name;
        this.registrationPlace = registrationPlace;
        this.address = address;
        this.crime = crime;
        this.imagePath = image;
    }
}
