package com.knightuser.security.jwt;

import com.knightuser.domain.RoleVO;
import com.knightuser.domain.UserVO;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static UserDetails create(UserVO userVO) {
        return JwtUser.builder()
            .id(userVO.getId())
            .username(userVO.getLogin())
            .password(userVO.getPassword())
            .authorities(mapToGrantedAuthorities(userVO.getRoles()))
            .build();
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<RoleVO> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
