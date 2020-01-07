package com.tt.shop.domain;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "Product.findAllByCategoryId",
            query = "FROM Product where CATEGORY_ID = :CATEGORY_ID")

@Entity
public class Product extends CreatedDate{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STORAGE_QUANTITY")
    private int storageQuantity;

    @Column(name = "PRICE")
    private BigDecimal price;

    @OneToMany(mappedBy = "product")
    private List<CartItem> itemsList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    public Product() {
    }

    public Product(String name, String description, int storageQuantity, BigDecimal price, Category category) {
        this.name = name;
        this.description = description;
        this.storageQuantity = storageQuantity;
        this.price = price;
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStorageQuantity() {
        return storageQuantity;
    }

    public void setStorageQuantity(int storageQuantity) {
        this.storageQuantity = storageQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<CartItem> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<CartItem> itemsList) {
        this.itemsList = itemsList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
