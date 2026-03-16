package com.yunfan.backend.user;

import com.yunfan.backend.user.dto.LoginRequest;
import com.yunfan.backend.user.dto.RegisterRequest;
import com.yunfan.backend.user.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@Valid @RequestBody RegisterRequest request) {
        UserResponse user = userService.register(request);

        return Map.of(
                "message", "User registered successfully",
                "user", user
        );
    }

    @PostMapping("/login")
    public Map<String, Object> login(@Valid @RequestBody LoginRequest request) {
        UserResponse user = userService.login(request);

        return Map.of(
                "message", "Login successful",
                "user", user
        );
    }
}