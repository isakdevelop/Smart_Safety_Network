package com.smartsafetynetwork.api.missingPerson.model;

import com.smartsafetynetwork.api.common.model.BaseEntity;
import com.smartsafetynetwork.api.missingPersonBoard.model.MissingPersonBoard;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@Builder
public class MissingPerson extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "age")
    private int age;

    @Column(name = "location")
    private String location;

    @Column(name = "date")
    private String date;

    @Embedded
    private Address address;

    @Embedded
    private Appearance appearance;


    @Column(name = "image_path")
    private String imagePath;

    @OneToMany(mappedBy = "missingPerson", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MissingPersonBoard> missingPersonBoards = new ArrayList<>();

    public void modify(String name, String gender, int age, String location, String date, String latitude, String longitude,
                  String address, Double height, Double weight, String physique, String faceShape, String hairColor,
                  String hairShape, String cloth, String imagePath) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.location = location;
        this.date = date;
        this.address = Address.builder()
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .build();
        this.appearance = Appearance.builder()
                .height(height)
                .weight(weight)
                .physique(physique)
                .faceShape(faceShape)
                .hairColor(hairColor)
                .hairShape(hairShape)
                .cloth(cloth)
                .build();
        this.imagePath = imagePath;
    }

}
