package com.smartsafetynetwork.api.user.model;

import com.smartsafetynetwork.api.common.model.BaseEntity;
import com.smartsafetynetwork.api.criminalBoard.model.CriminalBoard;
import com.smartsafetynetwork.api.missingPersonBoard.model.MissingPersonBoard;
import com.smartsafetynetwork.api.vulnerableRegion.model.VulnerableRegin;
import com.smartsafetynetwork.api.common.enums.Role;
import com.smartsafetynetwork.api.common.enums.SignType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Entity(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "gender", updatable = false)
    private String gender;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private SignType type;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VulnerableRegin> vulnerableRegins = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MissingPersonBoard> missingPersonBoards = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CriminalBoard> criminalBoards = new ArrayList<>();

    public void update(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    public void changedPasswordByEmail(String password) {
        this.password = password;
    }

}
