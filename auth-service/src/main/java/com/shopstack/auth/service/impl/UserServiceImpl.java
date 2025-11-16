package com.shopstack.auth.service.impl;

import com.shopstack.auth.domain.Role;
import com.shopstack.auth.domain.RoleType;
import com.shopstack.auth.domain.User;
import com.shopstack.auth.dto.RegisterRequest;
import com.shopstack.auth.dto.UserDto;
import com.shopstack.auth.dto.RegisterRequest;
import com.shopstack.auth.mapper.UserMapper;
import com.shopstack.auth.repository.RoleRepository;
import com.shopstack.auth.repository.UserRepository;
import com.shopstack.auth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           UserMapper userMapper ){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto register(RegisterRequest request){
        if (userRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists: " + request.email());
        }

        Role userRole = roleRepository.findByName(RoleType.USER)
                .orElseThrow(() -> new IllegalStateException("Default USER role missing"));

        User user = new User(
                request.email(),
                passwordEncoder.encode(request.password()),
                Set.of(userRole)
        );

        User saved = userRepository.save(user);

        return userMapper.toDto(saved);
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return userMapper.toDto(user);
    }
}