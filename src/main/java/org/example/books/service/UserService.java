package org.example.books.service;

import org.example.books.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    void saveUser(User user);

    User getUserByUsername(String username) throws Exception;

    UserDetailsService userDetailsService();
}
