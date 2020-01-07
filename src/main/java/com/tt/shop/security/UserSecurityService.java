package com.tt.shop.security;

import com.tt.shop.domain.User;
import com.tt.shop.domain.enumvalues.Role;
import com.tt.shop.exception.JwtValidateException;
import com.tt.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtTokenDto signin(String username, String password) throws JwtValidateException {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = checkUser(username);
            return new JwtTokenDto(jwtTokenProvider.createToken(username, createRolesList(user.getRole())));
        } catch (AuthenticationException e) {
            throw new JwtValidateException("Invalid username/password supplied");
        }
    }


    public User checkUser(String username) throws JwtValidateException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new JwtValidateException("The user doesn't exist in database");
        }
        return user;
    }

    public List<Role> createRolesList(Role role) {
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return roles;
    }

}


