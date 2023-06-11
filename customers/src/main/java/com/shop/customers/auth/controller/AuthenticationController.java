package com.shop.customers.auth.controller;

import com.shop.customers.auth.DTO.Request.ValidateRequest;
import com.shop.customers.auth.DTO.Response.AuthResponse;
import com.shop.customers.auth.DTO.Request.LoginRequest;
import com.shop.customers.auth.DTO.Response.ValidateResponse;
import com.shop.customers.auth.service.AuthService;
import com.shop.customers.model.CustomerModel;
import com.shop.customers.repository.AuthRepository;
import com.shop.customers.repository.CustomerRepository;
import com.shop.customers.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;
    private final JwtService jwtService;

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody CustomerModel customerModel){
        return ResponseEntity.ok(authService.register(customerModel));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/validate")
    public ResponseEntity<ValidateResponse> isValid(@RequestBody ValidateRequest validateRequest){
        String jwt = validateRequest.getToken();
        String userEmail = jwtService.extractEmail(jwt);


        Boolean checkValidation = jwtService.isTokenValid(jwt, customerRepository.findByEmail(userEmail));
        ValidateResponse validateResponse = new ValidateResponse();
        validateResponse.setIsValid(checkValidation);
        return new ResponseEntity<>(validateResponse, HttpStatus.OK);

    }
}
