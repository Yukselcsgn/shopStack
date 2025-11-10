package com.shopstack.catalog.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "brands")
public class Brand implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Brand() {}
    public Brand(String name) {this.name = name;}

    public Long getId() {return id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
}