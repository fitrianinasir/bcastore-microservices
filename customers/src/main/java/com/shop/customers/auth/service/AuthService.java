package com.shop.customers.auth.service;

import com.shop.customers.auth.DTO.Request.LoginRequest;
import com.shop.customers.auth.DTO.Response.AuthResponse;
import com.shop.customers.model.CustomerModel;
import com.shop.customers.model.Role;
import com.shop.customers.repository.CustomerRepository;
import com.shop.customers.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(CustomerModel customerModel){
        var user = CustomerModel.builder()
                .name(customerModel.getName())
                .email(customerModel.getEmail())
                .password(passwordEncoder.encode(customerModel.getPassword()))
                .pin(customerModel.getPin())
                .accountNumber(customerModel.getAccountNumber())
                .balance(customerModel.getBalance())
                .address(customerModel.getAddress())
                .city(customerModel.getCity())
                .insertDate(customerModel.getInsertDate())
                .role(Role.CUSTOMER)
                .build();
        customerRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }

    public AuthResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        var user = customerRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }
}
