package com.xacc.account.auth;

import com.xacc.account.auth.dto.AuthRegisterRequest;
import com.xacc.account.auth.dto.AuthRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final Map<String, AuthRegisterRequest> registeredUsers = new HashMap<>();

    private final AuthMapper authMapper;

    public AuthRegisterResponse register(AuthRegisterRequest dto) {
        if (!dto.password().equals(dto.confirmPassword())) {
            throw new IllegalArgumentException("Password tidak cocok");
        }

        String username = dto.username().toLowerCase(Locale.ROOT);

        if (registeredUsers.containsKey(username)) {
            throw new IllegalArgumentException("Username sudah terdaftar");
        }

        registeredUsers.put(username, dto);

        return authMapper.toRegisterResponse(dto);
    }

    public AuthRegisterResponse getByUsername(String username) {
        String normalizedUsername = username.toLowerCase(Locale.ROOT);

        AuthRegisterRequest user = registeredUsers.get(normalizedUsername);

        if (user == null) {
            throw new RuntimeException("Username or password is incorrect.");
        }

        return authMapper.toRegisterResponse(user);
    }
}