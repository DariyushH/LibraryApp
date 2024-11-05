package org.example.books.service.implementation;

import org.example.books.enums.UserRole;
import org.example.books.model.User;
import org.example.books.model.authorization.AuthRequest;
import org.example.books.model.authorization.JwtTokenResponse;
import org.example.books.service.AuthorizationService;
import org.example.books.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private final UserService userService;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthorizationServiceImpl(UserService userService, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public JwtTokenResponse signUp(AuthRequest req) {
        User user = new User(
                req.getUsername(),
                passwordEncoder.encode(req.getPassword()),
                UserRole.ROLE_USER);

        userService.saveUser(user);

        String jwt = jwtService.generateToken(user);
        return new JwtTokenResponse(jwt);
    }

    @Override
    public JwtTokenResponse signIn(AuthRequest req) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                req.getUsername(),
                req.getPassword()
        ));

        UserDetails user = userService
                .userDetailsService()
                .loadUserByUsername(req.getUsername());

        String jwt = jwtService.generateToken(user);
        return new JwtTokenResponse(jwt);
    }
}
