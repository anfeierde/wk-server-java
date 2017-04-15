package com.cz1.secruity.jwt;

import com.cz1.domain.Role;
import com.cz1.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by wkchen on 2017/3/22.
 */
public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
       return new JwtUser(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                mapToGrantedAuthority(user.getRoles()),
                user.getLastPasswordResetDate());
    }

    private static Set<GrantedAuthority> mapToGrantedAuthority(Set<Role> role) {
        return role.stream()
                .map(role1 -> new SimpleGrantedAuthority(role1.getName()))
                .collect(Collectors.toSet());
    }
}
