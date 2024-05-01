package com.smartsafetynetwork.api.common.component;

import com.smartsafetynetwork.api.admin.repository.AdminRepository;
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