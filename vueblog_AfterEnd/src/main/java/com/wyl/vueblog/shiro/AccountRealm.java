package com.wyl.vueblog.shiro;/**
 * @Auther:小王
 * @Date:2020/11/7
 * @Description:vueblog
 * @version:1.0
 */

import cn.hutool.core.bean.BeanUtil;
import com.wyl.vueblog.pojo.User;
import com.wyl.vueblog.service.UserService;
import com.wyl.vueblog.util.JwtUtils;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 授权认证
 **/
@Component
public class AccountRealm extends AuthorizingRealm {
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private UserService userService;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;
        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        User user = userService.getById(Long.valueOf(userId));
        if (user == null) {
            throw new UnknownAccountException("账户不存在");
        }
        if (user.getStatus() == -1) {
            System.out.println(user.getStatus()+"       status");
            throw new LockedAccountException("账户已锁定");
        }
        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(user,profile);
        System.out.println(getName()+"ssssssssssss");
        return new SimpleAuthenticationInfo(profile,jwtToken.getCredentials(), getName());
    }
}
