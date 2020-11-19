package com.wyl.vueblog.controller;


import com.sun.xml.internal.ws.api.pipe.ContentType;
import com.wyl.vueblog.common.lang.Result;
import com.wyl.vueblog.pojo.User;
import com.wyl.vueblog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author calf
 * @since 2020-11-04
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @RequiresAuthentication//需要登录才能访问
    @GetMapping("/index")
    public Result index() {
        System.out.println("66666");
        User user = userService.getById(1);
        return Result.success(200,"操作成功",user);
    }
    /**
     * 测试实体校验
     * @param user
     * @return
     */
    @PostMapping("/save")
    public Object testUser(@Validated @RequestBody User user) {
        System.out.println(user);
        //return user.toString();
        return Result.success(user);
    }

//    @GetMapping("/test")
//    public Object test() {
//        Integer[] nums=new Integer[]{1,2,3,4,5,6};
//        Stream<Integer> stream = Arrays.stream(nums);
//        System.out.println(stream.findAny());
//        return "stream";
//    }



}
