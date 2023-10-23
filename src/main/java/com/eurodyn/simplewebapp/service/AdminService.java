package com.eurodyn.simplewebapp.service;

import com.eurodyn.simplewebapp.model.SecurityAdmin;
import com.eurodyn.simplewebapp.repository.AdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements UserDetailsService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminRepository
                .findByUsername(username)
                .map(SecurityAdmin::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }

}
