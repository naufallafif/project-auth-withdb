package com.xacc.account.auth;

import com.xacc.account.auth.dto.AuthRegisterRequest;
import com.xacc.account.auth.dto.AuthRegisterResponse;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("{username}")
    public ResponseEntity<AuthRegisterResponse> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(authService.getByUsername(username));
    }

    @PostMapping("register")
    public ResponseEntity<AuthRegisterResponse> register(@RequestBody AuthRegisterRequest dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @GetMapping("/debug/session")
    // TODO buat inspect session
    public Map<String, Object> inspectSession(HttpSession http, Authentication authentication) {
        if (authentication == null) {
            return Map.of(
                    "sessionId: ", http.getId(),
                    "isNew: ", http.isNew(),
                    "authenticated: ", authentication != null && authentication.isAuthenticated());

        }

        return Map.of(
                "sessionId: ", http.getId(),
                "isNew: ", http.isNew(),
                "authenticated: ", authentication != null && authentication.isAuthenticated());
    }

    @GetMapping("/customer")
    public Map<String, Object> customerView(Authentication authentication) {
        return Map.of("hasil", authentication.getAuthorities());
    }

    @GetMapping("/seller")
    public Map<String, Object> sellerView(Authentication authentication) {
        return Map.of("hasil", authentication.getAuthorities());
    }

}
