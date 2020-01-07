package com.tt.shop.domain;

import com.tt.shop.domain.enumvalues.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends CreatedDate{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private String username;
    @Column
    private String password;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart = new Cart(this);

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserOrder> orders = new ArrayList<>();

    @Column
    private boolean isActive;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {};

    public User(String name, String lastName, String username, String password, boolean isActive, Role role) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<UserOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<UserOrder> orders) {
        this.orders = orders;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
