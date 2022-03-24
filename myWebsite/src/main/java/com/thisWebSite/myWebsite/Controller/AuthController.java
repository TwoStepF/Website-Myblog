package com.thisWebSite.myWebsite.Controller;

import com.thisWebSite.myWebsite.DTO.AuthenticationResponse;
import com.thisWebSite.myWebsite.DTO.LoginRequest;
import com.thisWebSite.myWebsite.DTO.RegisterRequest;
import com.thisWebSite.myWebsite.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RegisterRequest registerRequest){
        authService.SignUp(registerRequest);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
        return authService.Login(loginRequest);
    }
}
