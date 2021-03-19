package com.gemz.dev.service;

import com.gemz.dev.exception.ApiException;
import com.gemz.dev.model.Admin;
import com.gemz.dev.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AdminService implements UserDetailsService {

    @Autowired
    private AdminRepository repo;

    public List<Admin> getAll() {
        List<Admin> admins = repo.findAll();
        if (admins.isEmpty())
            throw new ApiException("No Admin Accounts found", HttpStatus.NO_CONTENT);
        return admins;
    }

    public Admin get(Long id) {
        try {
            return repo.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new ApiException("Admin with Id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }
    }

    public Admin add(Admin admin) {
        admin.setPassword(passwordEncoder().encode(admin.getPassword()));
        if (repo.findByUsername(admin.getUsername()) != null )
            throw new ApiException("Username " + admin.getUsername() + " is taken", HttpStatus.CONFLICT);
        return repo.save(admin);
    }

    public Admin findByUsername(String username){
        Admin admin = repo.findByUsername(username);
        if (admin == null)
            throw new ApiException("Username "+admin.getUsername()+" not found", HttpStatus.NOT_FOUND);
        return admin;
    }

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = repo.findByUsername(username);
        if (admin == null)
            throw new ApiException("Invalid username or password", HttpStatus.FORBIDDEN);
        return new org.springframework.security.core.userdetails.User(
                admin.getUsername(),
                admin.getPassword(),
                AuthorityUtils.NO_AUTHORITIES);
    }
}
