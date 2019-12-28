package com.tt.shop.repository;

import com.tt.shop.domain.UserOrder;
import org.springframework.data.repository.CrudRepository;

public interface UserOrderRepository extends CrudRepository<UserOrder, Long> {
}
