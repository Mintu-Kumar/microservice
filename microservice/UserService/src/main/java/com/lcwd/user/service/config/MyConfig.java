package com.lcwd.user.service.config;


import com.lcwd.user.service.interceptor.RestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyConfig {

    @Autowired
    private  ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    private  OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;
    // this bean is declare for to call another service from user service you can check UserServiceImpl class
    @Bean
    @LoadBalanced // Added this annotation to tell the spring user service name instead of localhost example    localhost:8080 - USER-SERVICE
    public RestTemplate restTemplate()
    {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptorList = new ArrayList<>();
        interceptorList.add(new RestTemplateInterceptor(manager(
            clientRegistrationRepository,
                oAuth2AuthorizedClientRepository
        )));
        restTemplate.setInterceptors(interceptorList);
        return restTemplate;
    }


    //declare the bean of OAuth2Authorization manager
    @Bean
    public OAuth2AuthorizedClientManager manager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository
    )
    {
        OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
       DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager = new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository,oAuth2AuthorizedClientRepository);
       defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);
       return defaultOAuth2AuthorizedClientManager;
    }
}
