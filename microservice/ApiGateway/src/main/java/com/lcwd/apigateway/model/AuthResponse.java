package com.lcwd.apigateway.model;

import lombok.*;

import java.util.Collection;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String userId;
    private String accessToken;
    private String regreshToken;
    private long expireAt;

    private Collection<String> authorities;
}
