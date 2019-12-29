package com.tt.shop.domain.enumvalues;

import com.tt.shop.exception.CartItemNotFoundException;

public class CartItemStatusFactory {

    public static CartItemStatus getCartItemStatus(int id) throws CartItemNotFoundException {

        switch (id) {
            case 1:
                return CartItemStatus.IN_CART;
            case 2:
                return CartItemStatus.DELETED_BY_USER;
            case 3:
                return CartItemStatus.DELETED_BY_ADMIN;
            case 4:
                return CartItemStatus.MODIFY_BY_ADMIN;
            default:
                throw new CartItemNotFoundException("Nieprawidłowy status pozycji w koszyku");
        }
    }

}
