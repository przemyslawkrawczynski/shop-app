package com.tt.shop.domain;

import com.tt.shop.domain.enumvalues.CartItemStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private int quantity;

    @Column
    private BigDecimal itemValue;

    @Enumerated(EnumType.STRING)
    private CartItemStatus cartItemStatus;

    public CartItem() {
    }

    public CartItem(Cart cart, Product products, int quantity, BigDecimal itemValue, CartItemStatus cartItemStatus) {
        this.cart = cart;
        this.product = products;
        this.quantity = quantity;
        this.itemValue = itemValue;
        this.cartItemStatus = cartItemStatus;
    }

    public CartItem(Cart cart, Product product, int quantity, BigDecimal itemValue) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.itemValue = itemValue;
        this.cartItemStatus = CartItemStatus.IN_CART;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getItemValue() {
        return itemValue;
    }

    public void setItemValue(BigDecimal itemValue) {
        this.itemValue = itemValue;
    }


    public CartItemStatus getCartItemStatus() {
        return cartItemStatus;
    }

    public void setCartItemStatus(CartItemStatus cartItemStatus) {
        this.cartItemStatus = cartItemStatus;
    }

}
