package com.shopstack.auth.controller;

import com.shopstack.auth.dto.UserDto;
import com.shopstack.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(Authentication auth) {
        String email = auth.getName();
        return ResponseEntity.ok(userService.findByEmail(email));
    }
}
