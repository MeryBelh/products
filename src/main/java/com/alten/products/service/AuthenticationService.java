package com.alten.products.service;

import com.alten.products.model.User;
import com.alten.products.model.request.SignUpRequest;
import com.alten.products.model.request.SigninRequest;
import com.alten.products.model.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
