package com.tt.shop.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserOrder extends CreatedDate  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public UserOrder() {}

    public UserOrder(User user) {
        this.user = user;
    }

    public UserOrder(Long id, User user, List<OrderItem> orderItems) {
        this.id = id;
        this.user = user;
        this.orderItems = orderItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
