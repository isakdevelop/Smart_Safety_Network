package com.smartsafetynetwork.api.component;

import com.smartsafetynetwork.api.repository.user.UserRepository;
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
