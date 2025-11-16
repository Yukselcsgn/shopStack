package com.shopstack.auth.repository;

import com.shopstack.auth.domain.User;
import com.shopstack.auth.dto.RegisterRequest;
import com.shopstack.auth.service.impl.UserServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}

//findByEmail → login ve JWT üretimi için şart.
//
//existsByEmail → register sırasında duplicate email engellemek için.