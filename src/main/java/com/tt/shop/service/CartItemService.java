package com.tt.shop.service;

import com.tt.shop.exception.CartNotFoundException;
import com.tt.shop.repository.CartItemRepository;
import com.tt.shop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public void deleteCartItemById(Long id) throws CartNotFoundException {
        try {
            cartItemRepository.deleteById(id);
        } catch (DataAccessException ex) {
            throw new CartNotFoundException("Nie udało się znaleźć carty o podanym ID: " + id);
        }
    }

}
