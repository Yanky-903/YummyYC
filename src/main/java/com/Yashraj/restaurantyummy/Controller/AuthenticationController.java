package com.Yashraj.restaurantyummy.Controller;

import com.Yashraj.restaurantyummy.dto.CustomerRequest;
import com.Yashraj.restaurantyummy.dto.LoginRequest;
import com.Yashraj.restaurantyummy.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(customerService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest.CustomerCreateRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
}

