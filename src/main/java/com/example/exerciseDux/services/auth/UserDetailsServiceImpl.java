package com.example.exerciseDux.services.auth;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("test".equals(username)) {
            return User
                    .withUsername("test")
                    .password(new BCryptPasswordEncoder().encode("12345"))
                    .authorities("ROLE_USER")
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
