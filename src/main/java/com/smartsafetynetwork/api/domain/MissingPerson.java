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
public class MissingPerson extends BaseEntity {
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

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "address")
    private String address;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "physique")
    private String physique;

    @Column(name = "faceShape")
    private String faceShape;

    @Column(name = "hairColor")
    private String hairColor;

    @Column(name = "hairShape")
    private String hairShape;

    @Column(name = "cloth")
    private String cloth;

    @Column(name = "image_path")
    private String imagePath;

    @OneToMany(mappedBy = "missingPerson")
    private List<MissingPersonBoard> missingPersonBoards = new ArrayList<>();

    public void modify(String name, String gender, int age, String location, String date, String latitude, String longitude,
                  String address, Double height, Double weight, String physique, String faceShape, String hairColor,
                  String hairShape, String cloth, String imagePath) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.location = location;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.height = height;
        this.weight = weight;
        this.physique = physique;
        this.faceShape = faceShape;
        this.hairColor = hairColor;
        this.hairShape = hairShape;
        this.cloth = cloth;
        this.imagePath = imagePath;
    }

}
