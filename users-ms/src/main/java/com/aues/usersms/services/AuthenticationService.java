package com.aues.usersms.services;


import com.aues.usersms.dto.JwtAuthenticationResponse;
import com.aues.usersms.dto.RefreshTokenRequest;
import com.aues.usersms.dto.SignInRequest;
import com.aues.usersms.dto.SignUpRequest;
import com.aues.usersms.model.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SignInRequest signInRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
