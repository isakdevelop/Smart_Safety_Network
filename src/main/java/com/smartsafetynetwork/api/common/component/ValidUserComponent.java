package com.smartsafetynetwork.api.common.component;

import com.smartsafetynetwork.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidUserComponent {
    private final UserRepository userRepository;

    public boolean isUser(String id) {
        return userRepository.existsById(id);
    }
}
