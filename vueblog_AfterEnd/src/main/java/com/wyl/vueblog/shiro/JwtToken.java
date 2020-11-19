package com.wyl.vueblog.shiro;/**
 * @Auther:小王
 * @Date:2020/11/7
 * @Description:vueblog
 * @version:1.0
 */

import org.apache.shiro.authc.AuthenticationToken;

/**
 **/
public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
