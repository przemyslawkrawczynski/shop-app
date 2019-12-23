package com.tt.shop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
    private User user;

    @OneToMany(
            mappedBy = "cart",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<CartItem> itemsInCart = new ArrayList<>();

    public Cart() {
    }

    public Cart(User user) {
        this.user = user;
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

    public List<CartItem> getItemsInCart() {
        return itemsInCart;
    }

    public void setItemsInCart(List<CartItem> itemsInCart) {
        this.itemsInCart = itemsInCart;
    }
}
