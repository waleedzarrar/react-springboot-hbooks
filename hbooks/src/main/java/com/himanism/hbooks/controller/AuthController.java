package com.himanism.hbooks.controller;


import com.himanism.hbooks.dto.request.LoginRequestDTO;
import com.himanism.hbooks.security.JwtService;
import com.himanism.hbooks.service.impl.CustomUserDetailsService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService,
                          CustomUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(Map.of("token", token, "tokenType", "Bearer"));
    }
}
