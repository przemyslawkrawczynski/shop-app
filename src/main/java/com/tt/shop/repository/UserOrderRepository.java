package com.tt.shop.repository;

import com.tt.shop.domain.UserOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserOrderRepository extends CrudRepository<UserOrder, Long> {

    List<UserOrder> findAll();
    List<UserOrder> findAllByUserId(Long id);

}
