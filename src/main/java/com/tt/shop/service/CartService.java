package com.tt.shop.service;

import com.tt.shop.domain.Cart;
import com.tt.shop.domain.CartItem;
import com.tt.shop.domain.dto.requestDto.AddCartItemDto;
import com.tt.shop.domain.dto.responseDto.CartDto;
import com.tt.shop.domain.enumvalues.CartItemStatus;
import com.tt.shop.exception.CartNotFoundException;
import com.tt.shop.exception.ProductNotFoundException;
import com.tt.shop.mapper.CartMapper;
import com.tt.shop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final UserService userService;
    private final ProductService productService;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Autowired
    public CartService(UserService userService, ProductService productService, CartRepository cartRepository, CartMapper cartMapper) {
        this.userService = userService;
        this.productService = productService;
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;

    }

    public Cart getCartById(Long id) throws CartNotFoundException {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        return optionalCart.orElseThrow(() -> new CartNotFoundException("Koszyk o podanym ID nie istnieje: "+ id));
    }

    public Cart getCartWithActiveItems(Long id) throws CartNotFoundException {
        Cart cart = getCartById(id);
        List<CartItem> onlyActiveItems = cart.getItemsInCart().stream()
                .filter(item -> item.getCartItemStatus().equals(CartItemStatus.IN_CART))
                .collect(Collectors.toList());
        cart.setItemsInCart(onlyActiveItems);
        return cart;
    }

    public void addItemToCart(AddCartItemDto cartItemDto) throws CartNotFoundException, ProductNotFoundException {

        Cart cart = getCartById(cartItemDto.getCart_id());
        cart.getItemsInCart().add(cartMapper.mapToCartItem(cart, cartItemDto));
        cartRepository.save(cart);
    }

    public CartDto getCartDtoWithActiveItems(Long cartId) throws CartNotFoundException {
        return cartMapper.mapToCartDto(getCartWithActiveItems(cartId));
    }
}
