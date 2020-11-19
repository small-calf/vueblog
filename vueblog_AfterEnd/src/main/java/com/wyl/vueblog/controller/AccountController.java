package com.wyl.vueblog.controller;/**
 * @Auther:小王
 * @Date:2020/11/10
 * @Description:vueblog
 * @version:1.0
 */

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wyl.vueblog.common.dto.LoginDto;
import com.wyl.vueblog.common.lang.Result;
import com.wyl.vueblog.pojo.User;
import com.wyl.vueblog.service.UserService;
import com.wyl.vueblog.util.JwtUtils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.session.mgt.SessionManager;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 **/
@RestController
@CrossOrigin
public class AccountController {
    @Resource
    private UserService userService;

    @Resource
    JwtUtils jwtUtils;
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        System.out.println("login");
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        //如果user为null就抛出异常
        Assert.notNull(user,"用户不存在");//断言： 断定某一个实际的值就为自己预期想得到的,如果不一样就抛出异常.

        //md5加密
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码不正确");
        }
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        // 用户可以另一个接口
        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .build()
        );

    }
    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.success("退出成功");
    }
}
