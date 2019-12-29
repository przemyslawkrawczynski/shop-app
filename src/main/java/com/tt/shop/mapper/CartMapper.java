package com.tt.shop.mapper;

import com.tt.shop.domain.Cart;
import com.tt.shop.domain.dto.responseDto.CartDto;
import com.tt.shop.domain.dto.responseDto.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartMapper {

    private final CartItemMapper cartItemMapper;

    @Autowired
    public CartMapper(CartItemMapper cartItemMapper) {
        this.cartItemMapper = cartItemMapper;
    }

    public CartDto mapToCartDto(Cart cart) {

        List<CartItemDto> cartItemDtos = cart.getItemsInCart().stream()
                .map(cartItemMapper::mapToCartItemDto)
                .collect(Collectors.toList());

        return new CartDto(
                cart.getId(),
                cart.getUser().getId(),
                cartItemDtos);
    }
}
