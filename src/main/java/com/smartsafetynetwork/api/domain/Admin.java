package com.smartsafetynetwork.api.domain;

import com.smartsafetynetwork.api.common.BaseEntity;
import com.smartsafetynetwork.api.domain.value.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "police")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Admin extends BaseEntity {

    @Column(name = "police_number", nullable = false, updatable = false, unique = true)
    private String policeNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public void modify(String name, String phone, String company, String department) {
        this.name = name;
        this.phone = phone;
        this.company = company;
        this.department = department;
    }
}
