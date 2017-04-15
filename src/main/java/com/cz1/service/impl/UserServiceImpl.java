package com.cz1.service.impl;

import com.cz1.domain.Role;
import com.cz1.domain.User;
import com.cz1.error.UserAlreadyExistsExcepition;
import com.cz1.repository.RoleRepository;
import com.cz1.repository.UserRepository;
import com.cz1.secruity.jwt.JwtAuthenticationRequest;
import com.cz1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wkchen on 2017/4/11.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * 用户注册
     * <p>
     * 1. 判断用户名是否已经被注册
     * 2. 加密密码
     * 3. 创建LastPasswordReset时间
     *
     * @param request
     * @return
     */
    @Override
    public User regiter(JwtAuthenticationRequest request) {
        if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new UserAlreadyExistsExcepition("User already exists");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setLastPasswordResetDate(new Date());
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        return userRepository.save(user);
    }


}
