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
    private String mail;
    @Column
    private String pass;

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

    public User(String name, String lastName, String mail, String pass, boolean isActive, Role role) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.pass = pass;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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
