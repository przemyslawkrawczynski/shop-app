package com.tt.shop.security;

import com.tt.shop.domain.User;
import com.tt.shop.domain.enumvalues.Role;
import com.tt.shop.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User: " + username + " not found in database.");
        }

        //Our app user has only one Role, but User(Security) needs to have List<Role>
        List<Role> roles = new ArrayList<>();
        roles.add(user.getRole());

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .authorities(roles)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}

