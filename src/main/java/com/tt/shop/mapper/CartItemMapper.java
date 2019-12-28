package com.tt.shop.mapper;

import com.tt.shop.domain.Cart;
import com.tt.shop.domain.CartItem;
import com.tt.shop.domain.Product;
import com.tt.shop.domain.dto.AddCartItemDto;
import com.tt.shop.domain.dto.CartItemDto;
import com.tt.shop.domain.enumvalues.CartItemStatus;
import com.tt.shop.exception.CartNotFoundException;
import com.tt.shop.exception.ProductNotFoundException;
import com.tt.shop.service.CartService;
import com.tt.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartItemMapper {

    private final ProductService productService;
    private final CartService cartService;
    private final ProductMapper productMapper;

    @Autowired
    public CartItemMapper(ProductService productService, CartService cartService, ProductMapper productMapper) {
        this.productService = productService;
        this.cartService = cartService;
        this.productMapper = productMapper;
    }

    public CartItem mapToCartItem(AddCartItemDto addCartItemDto) throws CartNotFoundException, ProductNotFoundException {

        Product product = productService.getProductById(addCartItemDto.getProduct_id());
        Cart cart = cartService.getCartById(addCartItemDto.getCart_id());
        BigDecimal itemValue = product.getPrice().multiply(new BigDecimal(addCartItemDto.getQuantity()));

        return new CartItem(cart, product, addCartItemDto.getQuantity(), itemValue, CartItemStatus.IN_CART);
    }

    public CartItemDto mapToCartItemDto(CartItem cartItem) {
        return new CartItemDto(cartItem.getId(),
                productMapper.mapToProductDto(cartItem.getProduct()),
                cartItem.getQuantity(),
                cartItem.getItemValue());
    }
}
