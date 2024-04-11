package com.smartsafetynetwork.api.domain;

import com.smartsafetynetwork.api.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class MissingPersonBoard extends BaseEntity {
    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String address;

    @Column
    private String latitude;

    @Column
    private String longitude;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "missingPerson_id")
    private MissingPerson missingPerson;

    public void modify(String title, String content, String address, String latitude, String longitude) {
        this.title = title;
        this.content = content;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
