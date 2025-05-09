package org.example.books.controller;

import org.example.books.model.authorization.AuthRequest;
import org.example.books.model.authorization.JwtTokenResponse;
import org.example.books.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    private final AuthorizationService authService;
    @Autowired
    public AuthorizationController(AuthorizationService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public JwtTokenResponse signIn(@RequestBody AuthRequest request) {
        return authService.signIn(request);
    }

    @PostMapping("/signup")
    public JwtTokenResponse signUp(@RequestBody AuthRequest request) {
        return authService.signUp(request);
    }
}
