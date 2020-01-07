package com.tt.shop.service;

import com.sun.org.apache.bcel.internal.generic.ARETURN;
import com.tt.shop.domain.Cart;
import com.tt.shop.domain.User;
import com.tt.shop.exception.UserNotFoundException;
import com.tt.shop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public boolean isExistById(Long id) {
        return userRepository.existsById(id);
    }

    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> opt = userRepository.findById(id);
        return opt.orElseThrow(() -> new UserNotFoundException("Nie odnaleziono użytkownika o ID: " + id));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Cart getUserCartByUserId(Long id) throws UserNotFoundException {
        return getUserById(id).getCart();
    }

    public long countAllUsers() {
        return userRepository.count();
    }

    public List<User> getAllUserList() {
        return userRepository.findAll();
    }
}
