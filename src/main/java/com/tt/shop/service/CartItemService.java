package com.tt.shop.service;

import com.tt.shop.domain.CartItem;
import com.tt.shop.domain.enumvalues.CartItemStatus;
import com.tt.shop.exception.CartItemNotFoundException;
import com.tt.shop.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

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
        cartItemRepository.save(cartItem);
    }

    public void update(Long id, Integer quantity) throws CartItemNotFoundException {
        CartItem cartItem = getCartItemById(id);
        BigDecimal newItemValue = cartItem.getProduct().getPrice().multiply(new BigDecimal(quantity));
        cartItem.setQuantity(quantity);
        cartItem.setItemValue(newItemValue);
        cartItemRepository.save(cartItem);
    }

    public void setStatusAfterOrder(List<CartItem> cartItems) {
        cartItems.stream()
                .forEach(item -> item.setCartItemStatus(CartItemStatus.ORDERED));
        cartItemRepository.saveAll(cartItems);
    }


}
