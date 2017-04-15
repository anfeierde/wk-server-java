package com.cz1.service;

import com.cz1.domain.User;
import com.cz1.secruity.jwt.JwtAuthenticationRequest;

/**
 * Created by wkchen on 2017/4/11.
 */
public interface UserService {

    User regiter(JwtAuthenticationRequest request);
}
