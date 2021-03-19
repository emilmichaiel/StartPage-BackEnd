package com.gemz.dev.controller;

import com.gemz.dev.Util.JwtTokenUtil;
import com.gemz.dev.dto.Jwt;
import com.gemz.dev.dto.Login;
import com.gemz.dev.exception.ApiException;
import com.gemz.dev.model.Admin;
import com.gemz.dev.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "${cors.origin}")
public class AuthController{

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AdminService service;
    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("")
    public Jwt logIn(@RequestBody Login login){
        String token = "";
        Admin admin = new Admin();
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = service.loadUserByUsername(login.getUsername());
            admin = service.findByUsername(login.getUsername());
            token = jwtTokenUtil.generateToken(userDetails.getUsername());
        } catch (Exception ex) {
            throw new ApiException("Invalid username or password", HttpStatus.FORBIDDEN);
        }
        return new Jwt(token, admin);
    }

    @PostMapping("/register")
    public ResponseEntity<Admin> register(@Valid @RequestBody Admin admin) {
        return new ResponseEntity<>(service.add(admin), HttpStatus.CREATED);
    }

}
