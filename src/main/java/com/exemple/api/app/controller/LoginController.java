package com.exemple.api.app.controller;

import com.exemple.api.app.controller.form.LoginInput;
import com.exemple.api.domain.service.LoginService;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@Profile("prod")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginInput loginInput) {
        String token = loginService.login(loginInput.getEmail(), loginInput.getPassword());
        return ResponseEntity.ok().header("Access-token", token).build();
    }
}
