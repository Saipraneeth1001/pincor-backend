package com.pincor.address.now.request;

public class AuthenticationResponse {
    private String jwt;

    public AuthenticationResponse(String token) {
        this.jwt = token;
    }

    public String getJwt() {
        return jwt;
    }
}
