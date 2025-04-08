package org.example.books.model.authorization;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthRequest {
    private String username;
    private String password;
}
