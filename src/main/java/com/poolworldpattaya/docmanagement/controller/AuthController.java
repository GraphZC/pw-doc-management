package com.poolworldpattaya.docmanagement.controller;

import com.poolworldpattaya.docmanagement.entity.Employee;
import com.poolworldpattaya.docmanagement.request.LoginRequest;
import com.poolworldpattaya.docmanagement.service.AuthService;
import com.poolworldpattaya.docmanagement.service.EmployeeService;
import com.poolworldpattaya.docmanagement.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/create")
    public String addNewUser(@RequestBody Employee userInfo) {
        return authService.addUser(userInfo);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(request.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

}
