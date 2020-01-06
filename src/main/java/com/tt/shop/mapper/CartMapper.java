package com.tt.shop.mapper;

import com.tt.shop.domain.Cart;
import com.tt.shop.domain.CartItem;
import com.tt.shop.domain.Product;
import com.tt.shop.domain.dto.requestDto.AddCartItemDto;
import com.tt.shop.domain.dto.responseDto.CartDto;
import com.tt.shop.domain.dto.responseDto.CartItemDto;
import com.tt.shop.domain.enumvalues.CartItemStatus;
import com.tt.shop.exception.CartNotFoundException;
import com.tt.shop.exception.ProductNotFoundException;
import com.tt.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartMapper {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @Autowired
    public CartMapper(ProductMapper productMapper, ProductService productService) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    public CartDto mapToCartDto(Cart cart) {

        List<CartItemDto> cartItemDtos = cart.getItemsInCart().stream()
                .map(this::mapToCartItemDto)
                .collect(Collectors.toList());

        BigDecimal cartValue = cartItemDtos.stream()
                .map(CartItemDto::getItemValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CartDto(
                cart.getId(),
                cart.getUser().getId(),
                cartItemDtos, cartValue);
    }

    public CartItemDto mapToCartItemDto(CartItem cartItem) {
        return new CartItemDto(cartItem.getId(),
                productMapper.mapToProductDto(cartItem.getProduct()),
                cartItem.getQuantity(),
                cartItem.getItemValue());
    }

    public CartItem mapToCartItem(Cart cart, AddCartItemDto addCartItemDto) throws CartNotFoundException, ProductNotFoundException {

        Product product = productService.getProductById(addCartItemDto.getProduct_id());
        BigDecimal itemValue = product.getPrice().multiply(new BigDecimal(addCartItemDto.getQuantity()));

        return new CartItem(cart, product, addCartItemDto.getQuantity(), itemValue, CartItemStatus.IN_CART);
    }
}
