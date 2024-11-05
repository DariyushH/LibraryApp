package org.example.books.service.implementation;

import org.example.books.model.User;
import org.example.books.repository.UserRepository;
import org.example.books.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findFirstByUsername(username).orElse(null);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::getUserByUsername;
    }
}
