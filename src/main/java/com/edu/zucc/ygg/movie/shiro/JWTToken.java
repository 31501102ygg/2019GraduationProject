package com.edu.zucc.ygg.movie.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class JWTToken implements AuthenticationToken {

    //用户类型 (0:admin, 1:user)
    private Integer type;

    //行为 (0:login, 1:other)
    private Integer behavior;

    // 密钥
    private String token;

    public JWTToken( String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return type;
    }

    public Integer getBehavior() {
        return behavior;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
