package com.example.onepipeproject.security;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        if (authentication.getUserAuthentication() != null) {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            additionalInfo.put("first_name", userPrincipal.getFirstname());
            additionalInfo.put("last_name", userPrincipal.getLastname());

            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(
                    additionalInfo);
        }

        return accessToken;
    }
}
