package com.example.projectagile.service;

import com.example.projectagile.exception.DataNotFoundException;
import com.example.projectagile.model.User;
import com.example.projectagile.repository.UserRepository;
import com.example.projectagile.service.impl.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public User login(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new DataNotFoundException("Invalid username or password");
        }
        // return optionalUser.get();
        User existingUser = optionalUser.get();
        // check password
        if (!passwordEncoder.matches(password, existingUser.getPassword())) {
            throw new BadCredentialsException("Wrong phone number or password");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username, password, existingUser.getAuthorities()
        );
        // authenticate with spring security
        authenticationManager.authenticate(authenticationToken);
        return existingUser;
    }
}
