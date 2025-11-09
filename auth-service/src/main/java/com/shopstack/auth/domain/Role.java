package com.shopstack.auth.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")

public class Role extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleType name;

    public Role(){}

    public Role(RoleType name){
        this.name = name;
    }

    public Long getId() {return id;}

    public RoleType getName() {
        return name;
    }

    public void setName(String name) {}
}