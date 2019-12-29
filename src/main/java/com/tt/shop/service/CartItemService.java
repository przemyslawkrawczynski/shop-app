package com.tt.shop.service;

import com.tt.shop.domain.CartItem;
import com.tt.shop.domain.enumvalues.CartItemStatus;
import com.tt.shop.domain.enumvalues.CartItemStatusFactory;
import com.tt.shop.exception.CartItemNotFoundException;
import com.tt.shop.exception.CartNotFoundException;
import com.tt.shop.repository.CartItemRepository;
import com.tt.shop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;


    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }


    public CartItem getCartItemById(Long id) throws CartItemNotFoundException {
        Optional<CartItem> opt = cartItemRepository.findById(id);
        return opt.orElseThrow(() -> new CartItemNotFoundException("Nie udało się odnaleźć pozycji o podanym ID: " + id));
    }

    public void removeCartItemById(Long id) throws CartItemNotFoundException {
        CartItem cartItem = getCartItemById(id);
        cartItem.setCartItemStatus(CartItemStatus.DELETED_BY_USER);
        System.out.println(cartItem.getCartItemStatus());
        cartItemRepository.save(cartItem);
    }

    public void setStatusAfterOrder(List<CartItem> cartItems) {
        cartItems.stream()
                .forEach(item -> item.setCartItemStatus(CartItemStatus.ORDERED));
        cartItemRepository.saveAll(cartItems);
    }

    public List<CartItem> getAllActiveCartItemsInCart(Long cartId) throws CartItemNotFoundException {
        return cartItemRepository.findAllByCart_IdAndCartItemStatus(cartId, CartItemStatusFactory.getCartItemStatus(1));
    }


}
