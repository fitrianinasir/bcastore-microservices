package com.shop.products.dto.Data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenData {
    public String token;
}
