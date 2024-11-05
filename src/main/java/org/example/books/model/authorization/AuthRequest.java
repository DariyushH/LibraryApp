package org.example.books.model.authorization;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
