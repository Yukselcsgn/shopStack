package com.shopstack.auth.service;

import com.shopstack.auth.dto.UserDto;
import com.shopstack.auth.dto.RegisterRequest;

public interface UserService {

    UserDto register(RegisterRequest request);

    UserDto findByEmail(String email);
}