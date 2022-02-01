package com.exemple.api.controller;

import com.exemple.api.controller.dto.HomeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {

    @GetMapping
    public ResponseEntity<HomeDto> readAll() {
        return ResponseEntity.ok(new HomeDto());
    }

}
