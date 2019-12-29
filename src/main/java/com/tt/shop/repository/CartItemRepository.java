package com.tt.shop.repository;

import com.tt.shop.domain.CartItem;
import com.tt.shop.domain.enumvalues.CartItemStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {

    List<CartItem> findAllByCart_IdAndCartItemStatus(Long cartId, CartItemStatus cartItemStatus);

}
