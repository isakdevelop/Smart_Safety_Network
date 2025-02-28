package com.smartsafetynetwork.api.missingPerson.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Embeddable
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@DynamicUpdate
@Builder
public class Appearance {
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
}
