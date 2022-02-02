package com.exemple.api.service;

import com.exemple.api.common.helper.JWTHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImp implements LoginService {

    private final AuthenticationManager authManager;
    private final JWTHelper jwtHelper;

    public LoginServiceImp(AuthenticationManager authManager, JWTHelper jwtHelper) {
        this.authManager = authManager;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public String login(String email, String password) {
        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authManager.authenticate(upat);

        return "Bearer " + jwtHelper.generate(authentication.getName());
    }
}
