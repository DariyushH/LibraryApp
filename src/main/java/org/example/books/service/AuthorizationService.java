package org.example.books.service;

import org.example.books.model.authorization.AuthRequest;
import org.example.books.model.authorization.JwtTokenResponse;

public interface AuthorizationService {
    JwtTokenResponse signUp(AuthRequest req);

    JwtTokenResponse signIn(AuthRequest req);
}
