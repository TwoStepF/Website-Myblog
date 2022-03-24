package com.thisWebSite.myWebsite.Service;

import com.thisWebSite.myWebsite.DTO.AuthenticationResponse;
import com.thisWebSite.myWebsite.DTO.LoginRequest;
import com.thisWebSite.myWebsite.DTO.RegisterRequest;
import com.thisWebSite.myWebsite.model.User;
import com.thisWebSite.myWebsite.repository.userRepository;
import com.thisWebSite.myWebsite.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {


    @Autowired
    private userRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    private String encodeP(String password) {
        return passwordEncoder.encode(password);
    }

    public void SignUp(RegisterRequest registerRequest) {
        User user = new User(registerRequest.getUsername(), encodeP(registerRequest.getPassword()));
        userRepository.save(user);
    }

    public AuthenticationResponse Login(LoginRequest loginRequest) {
        User user = userRepository.findByUserName(loginRequest.getUsername()).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername(), user.getRole());
    }

    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }
}
