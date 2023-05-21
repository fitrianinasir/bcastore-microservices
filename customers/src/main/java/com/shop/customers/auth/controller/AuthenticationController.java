package com.shop.customers.auth.controller;

import com.shop.customers.auth.DTO.Response.AuthResponse;
import com.shop.customers.auth.DTO.Request.LoginRequest;
import com.shop.customers.auth.service.AuthService;
import com.shop.customers.model.CustomerModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody CustomerModel customerModel){
        return ResponseEntity.ok(authService.register(customerModel));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
