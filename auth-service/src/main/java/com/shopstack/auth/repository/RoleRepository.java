package com.shopstack.auth.repository;

import com.shopstack.auth.domain.Role;
import com.shopstack.auth.domain.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleType name);
}

//Yeni kullanıcı oluşturulurken varsayılan rol almak için kullanılır (USER veya ADMIN).