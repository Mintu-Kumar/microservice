package com.lcwd.apigateway.controller;


import com.lcwd.apigateway.model.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    //Logger logger =  new LoggerFactory.getLogger(AuthController.class)
    @GetMapping("/login")
        public ResponseEntity<AuthResponse> login(
           @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
           @AuthenticationPrincipal OidcUser user,
           Model model
    ){

        System.out.println("User email Id="+ user.getEmail());

        // ceating auth response object
        AuthResponse response = new AuthResponse();
        //setting email to authresponse
        response.setUserId(user.getEmail());
        //setting token to auth response
        response.setAccessToken(client.getAccessToken().getTokenValue());

        response.setRegreshToken(client.getRefreshToken().getTokenValue());

        response.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());

         List<String> authorities = user.getAuthorities().stream().map(grantedAuthority -> {
             return grantedAuthority.getAuthority();

         }).collect(Collectors.toList());

         response.setAuthorities(authorities);

       return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
