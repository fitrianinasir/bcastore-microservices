package com.api.gateway.controller;

import com.api.gateway.dto.ResponseMessage;
import com.api.gateway.dto.ResponseTokenValid;
import com.api.gateway.dto.data.Token;
import com.api.gateway.service.GWService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("api-gw")
@RequiredArgsConstructor

public class APIGWController {

    @Autowired
    GWService gwService;

    @GetMapping("products")
    public Mono<ResponseTokenValid> getAllProducts(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ){
        System.out.println("calling controller");
        Token tokenData = new Token();
        tokenData.setToken(token.substring(7));
        return gwService.checkIsTokenValid(tokenData);
    }
}
