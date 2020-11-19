package com.wyl.vueblog.service.impl;

import com.wyl.vueblog.pojo.User;
import com.wyl.vueblog.mapper.UserMapper;
import com.wyl.vueblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author calf
 * @since 2020-11-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
