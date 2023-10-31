package com.poolworldpattaya.docmanagement.controller;

import com.poolworldpattaya.docmanagement.entity.Employee;
import com.poolworldpattaya.docmanagement.request.LoginRequest;
import com.poolworldpattaya.docmanagement.request.RefreshTokenRequest;
import com.poolworldpattaya.docmanagement.response.LoginResponse;
import com.poolworldpattaya.docmanagement.service.AuthService;
import com.poolworldpattaya.docmanagement.service.EmployeeDetails;
import com.poolworldpattaya.docmanagement.service.EmployeeService;
import com.poolworldpattaya.docmanagement.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public String addNewUser(@RequestBody Employee userInfo) {
        return authService.addUser(userInfo);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );

        if (authentication.isAuthenticated()) {
            Employee employee = employeeService.getByUsername(request.getUsername());
            LoginResponse response = new LoginResponse();

            String accessToken = jwtService.generateAccessToken(request.getUsername(), employee.getRoles(), employee.getName());
            String refreshToken = jwtService.generateRefreshToken(request.getUsername(), employee.getRoles(), employee.getName());

            response.setId(employee.getId());
            response.setUsername(employee.getUsername());
            response.setName(employee.getName());
            response.setRoles(employee.getRoles());
            response.setAccessToken(accessToken);
            response.setAccessTokenExpires(jwtService.extractExpiration(accessToken));
            response.setRefreshToken(refreshToken);
            response.setRefreshTokenExpires(jwtService.extractExpiration(refreshToken));

            return ResponseEntity.ok(response);
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@RequestBody RefreshTokenRequest request) {
        LoginResponse response = new LoginResponse();
        String oldRefreshToken =  request.getRefreshToken();

        if (!jwtService.isTokenExpired(oldRefreshToken)) {
            String username = jwtService.extractUsername(oldRefreshToken);
            Employee employee = employeeService.getByUsername(username);
            String roles = employee.getRoles();
            String name = employee.getName();
            UUID id = employee.getId();

            String accessToken = jwtService.generateAccessToken(username, roles, name);
            String refreshToken = jwtService.generateRefreshToken(username, roles, name);

            response.setId(id);
            response.setUsername(username);
            response.setRoles(roles);
            response.setName(name);
            response.setAccessToken(accessToken);
            response.setAccessTokenExpires(jwtService.extractExpiration(accessToken));
            response.setRefreshToken(refreshToken);
            response.setRefreshTokenExpires(jwtService.extractExpiration(refreshToken));
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }

        return ResponseEntity.ok(response);
    }

}
