package com.tt.shop.service;

import com.tt.shop.domain.Cart;
import com.tt.shop.domain.CartItem;
import com.tt.shop.domain.Category;
import com.tt.shop.domain.Product;
import com.tt.shop.domain.enumvalues.CartItemStatus;
import com.tt.shop.exception.CartItemNotFoundException;
import com.tt.shop.repository.CartItemRepository;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CartServiceTestSuits {

    @Test
    public void testGetCartItemById() throws CartItemNotFoundException {
        //Given
        CartItemRepository cartItemRepositoryMock = mock(CartItemRepository.class);
        CartItemService cartItemService = new CartItemService(cartItemRepositoryMock);
        CartItem cartItem = new CartItem(new Cart(), new Product(), 12, new BigDecimal(12));
        when(cartItemRepositoryMock.findById(1L)).thenReturn(Optional.of(cartItem));

        //When
        CartItem result = cartItemService.getCartItemById(1L);

        //Then
        Assert.assertEquals(CartItemStatus.IN_CART, result.getCartItemStatus());

    }

    @Test
    public void testRemoveCartItemById() throws CartItemNotFoundException {
        //Given
        CartItemRepository cartItemRepositoryMock = mock(CartItemRepository.class);
        CartItemService cartItemService = new CartItemService(cartItemRepositoryMock);
        CartItem cartItem = new CartItem(new Cart(), new Product(), 12, new BigDecimal(12));
        when(cartItemRepositoryMock.findById(1L)).thenReturn(Optional.of(cartItem));

        //When
        cartItemService.removeCartItemById(1L);

        //Then
        verify(cartItemRepositoryMock, times(1)).findById(anyLong());
        verify(cartItemRepositoryMock, times(1)).save(any(CartItem.class));
    }

    @Test
    public void testUpdate() throws CartItemNotFoundException {

        //Given
        CartItemRepository cartItemRepositoryMock = mock(CartItemRepository.class);
        CartItemService cartItemService = new CartItemService(cartItemRepositoryMock);
        CartItem cartItem = new CartItem(new Cart(),
                new Product("P1", "Desc", 12, new BigDecimal(12), new Category("Cat")),
                12,
                new BigDecimal("120")
        );
        when(cartItemRepositoryMock.findById(1L)).thenReturn(Optional.of(cartItem));

        //When
        cartItemService.update(1L, 15);

        verify(cartItemRepositoryMock, times(1)).save(any(CartItem.class));
        Assert.assertEquals(15, cartItem.getQuantity());

    }

    @Test
    public void testSetStatusAfterOrder() {

        //Given
        CartItemRepository cartItemRepositoryMock = mock(CartItemRepository.class);
        CartItemService cartItemService = new CartItemService(cartItemRepositoryMock);

        CartItem cartItem = new CartItem(new Cart(),
                new Product("P1", "Desc", 12, new BigDecimal(12), new Category("Cat")),
                12,
                new BigDecimal("120")
        );
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(cartItem);

        //When
        cartItemService.setStatusAfterOrder(cartItemList);
        verify(cartItemRepositoryMock, times(1)).saveAll(cartItemList);
        Assert.assertEquals(CartItemStatus.ORDERED, cartItem.getCartItemStatus());

    }
}


