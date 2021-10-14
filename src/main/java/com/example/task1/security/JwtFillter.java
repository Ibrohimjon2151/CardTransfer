package com.example.task1.security;

import com.example.task1.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFillter extends OncePerRequestFilter {
    @Autowired
    JwtToken jwtToken;

    @Autowired
    AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token =request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer")){
            token = token.substring(0,7);
            boolean validateToket = jwtToken.validateToken(token);

            if (validateToket) {

                String userName = jwtToken.getUserNameByToken(token);

                UserDetails userDetails = authService.loadUserByUsername(userName);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails , null , userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }
        filterChain.doFilter(request , response);


    }
}
