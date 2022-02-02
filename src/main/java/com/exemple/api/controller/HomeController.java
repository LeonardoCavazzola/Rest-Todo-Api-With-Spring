package com.exemple.api.controller;

import com.exemple.api.controller.dto.HomeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {

    @GetMapping
    public ResponseEntity<HomeResponse> homeLinks() {
        return ResponseEntity.ok(new HomeResponse());
    }

}
