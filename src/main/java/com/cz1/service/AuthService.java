package com.cz1.service;

/**
 * Created by wkchen on 2017/4/10.
 */
public interface AuthService {

    String createToken(String username,String password);

    String refreshToken(String token);
}
