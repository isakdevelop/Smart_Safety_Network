package com.smartsafetynetwork.api.component;

import com.smartsafetynetwork.api.repository.admin.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidAdminComponent {
    private final AdminRepository adminRepository;

    public boolean isAdmin(String id) {
        return adminRepository.existsById(id);
    }
}