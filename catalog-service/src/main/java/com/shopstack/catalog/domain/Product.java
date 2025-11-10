package com.shopstack.catalog.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Double price;

    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public Product() {}

    public Product(String name, String description, Double price, Integer stock,
                   Category category, Brand brand) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.brand = brand;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public Double getPrice() { return price; }

    public Integer getStock() { return stock; }

    public Category getCategory() { return category; }

    public Brand getBrand() { return brand; }

    public String getDescription() { return description; }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

    public void setPrice(Double price) { this.price = price; }

    public void setStock(Integer stock) { this.stock = stock; }

    public void setCategory(Category category) { this.category = category; }

    public void setBrand(Brand brand) { this.brand = brand; }
}