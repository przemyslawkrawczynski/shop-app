package com.tt.shop.service;

import com.tt.shop.domain.Cart;
import com.tt.shop.domain.CartItem;
import com.tt.shop.exception.CartNotFoundException;
import com.tt.shop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final UserService userService;
    private final ProductService productService;
    private final CartRepository cartRepository;

    @Autowired
    public CartService(UserService userService, ProductService productService, CartRepository cartRepository) {
        this.userService = userService;
        this.productService = productService;
        this.cartRepository = cartRepository;
    }

    public Cart getCartById(Long id) throws CartNotFoundException {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        return optionalCart.orElseThrow(() -> new CartNotFoundException("Nie udało się dodać produktu, koszyk o podanym ID nie istnieje:  + id"));
    }

    public void addItemToCart(CartItem cartItem) throws CartNotFoundException {
        Cart cart = getCartById(cartItem.getCart().getId());
        cart.getItemsInCart().add(cartItem);
        cartRepository.save(cart);
    }

    public void deleteItemFromCart(Long cart_id, Long item_id) {

    }
}
