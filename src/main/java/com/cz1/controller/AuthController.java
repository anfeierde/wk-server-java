package com.cz1.controller;

import com.cz1.secruity.jwt.JwtAuthenticationResponce;
import com.cz1.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wkchen on 2017/4/10.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    /**
     * 用户认证，生成Token
     * @param username
     * @param password
     * @return
     */
    @GetMapping(value = "/{username}")
    public ResponseEntity<?> authorize(@PathVariable("username") String username,
                                       @RequestParam("password") String password) throws AuthenticationException {
        String token = authService.createToken(username, password);
        return ResponseEntity.ok(new JwtAuthenticationResponce(token));
    }

    /**
     * 重新认证
     * @param httpServletRequest
     * @return
     */
    @GetMapping
    public ResponseEntity<?> reauthorize(HttpServletRequest httpServletRequest) throws AuthenticationException {
        String token = httpServletRequest.getHeader(tokenHeader);
        String newToken = authService.refreshToken(token);
        if (newToken != null) {
            return ResponseEntity.ok(new JwtAuthenticationResponce(token));
        }
        return ResponseEntity.ok(null);
    }
}
