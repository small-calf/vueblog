package com.wyl.vueblog.service.impl;

import com.wyl.vueblog.pojo.Blog;
import com.wyl.vueblog.mapper.BlogMapper;
import com.wyl.vueblog.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
