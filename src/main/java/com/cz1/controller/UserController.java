package com.cz1.controller;

import com.cz1.secruity.jwt.JwtAuthenticationRequest;
import com.cz1.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wkchen on 2017/4/7.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 用户注册
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<?> register(@RequestBody JwtAuthenticationRequest request) {
        return ResponseEntity.ok(userService.regiter(request));
    }
}
