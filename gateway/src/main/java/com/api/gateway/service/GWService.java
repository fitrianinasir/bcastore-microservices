package com.api.gateway.service;

import com.api.gateway.dto.ResponseTokenValid;
import com.api.gateway.dto.data.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Media;

@Service
public class GWService {

    @Value("${auth.url}")
    private String authUrl;

    private final WebClient webClient;


    public GWService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.build();
    }


    public Mono<ResponseTokenValid> checkIsTokenValid(Token token){
        return webClient.post()
                .uri(authUrl)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(token), Token.class)
                .retrieve()
                .bodyToMono(ResponseTokenValid.class);
    }
}
