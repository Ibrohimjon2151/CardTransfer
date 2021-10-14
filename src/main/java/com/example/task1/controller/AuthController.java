package com.example.task1.controller;

import com.example.task1.peliod.LoginDTO;
import com.example.task1.security.JwtFillter;
import com.example.task1.security.JwtToken;
import com.example.task1.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    JwtToken jwtToken;

    @Autowired
    AuthService authService;

    @Autowired
    JwtFillter jwtFillter;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping
    public HttpEntity<?> add(@RequestBody LoginDTO loginDTO){
       try {
           authService.loadUserByUsername(loginDTO.getUserName());
           UsernamePasswordAuthenticationToken checkOut = new UsernamePasswordAuthenticationToken(loginDTO.getUserName() , loginDTO.getPassword());
           authenticationManager.authenticate(checkOut);
           String token =jwtToken.tokenGenerator(loginDTO.getUserName());
           return ResponseEntity.ok(token);
       }catch (Exception e){
            return ResponseEntity.status(401).body("Login Yoki Password hato");
       }

    }

}
