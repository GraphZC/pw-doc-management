package com.poolworldpattaya.docmanagement.service;

import com.poolworldpattaya.docmanagement.entity.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDetails implements org.springframework.security.core.userdetails.UserDetails {
    private String name;
    private String password;
    private List<GrantedAuthority> authorities;

    public EmployeeDetails(Employee employee) {
        this.name = employee.getName();
        this.password = employee.getPassword();
        authorities = Arrays.stream(employee.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
