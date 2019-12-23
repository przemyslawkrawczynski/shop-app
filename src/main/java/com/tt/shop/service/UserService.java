package com.tt.shop.service;

import com.tt.shop.domain.User;
import com.tt.shop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public long countAllUsers() {
        return userRepository.count();
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }
}
