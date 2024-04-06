package com.smartsafetynetwork.api.domain;

import com.smartsafetynetwork.api.common.BaseEntity;
import com.smartsafetynetwork.api.domain.value.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class User extends BaseEntity {

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "gender", nullable = false, updatable = false)
    private String gender;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone", unique = true, nullable = false)
    private String phone;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<VulnerableRegin> vulnerableRegins = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<MissingPersonBoard> missingPersonBoards = new ArrayList<>();

    public void update(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    public void changedPasswordByEmail(String password) {
        this.password = password;
    }

}
