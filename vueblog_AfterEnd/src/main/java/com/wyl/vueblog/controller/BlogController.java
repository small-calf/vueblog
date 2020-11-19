package com.wyl.vueblog.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyl.vueblog.common.lang.Result;
import com.wyl.vueblog.pojo.Blog;
import com.wyl.vueblog.service.BlogService;
import com.wyl.vueblog.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author calf
 * @since 2020-11-04
 */
@RestController
public class BlogController {
    @Resource
    private BlogService blogService;

    /**
     * 列表
     * @return
     */
    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page page = new Page(currentPage,5);//分页功能
        Page pageDate = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.success(pageDate);
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"该博客以被删除");
        return Result.success(blog);
    }

    /**
     * 编辑  添加
     * @param blog
     * @return
     */
    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result list(@Validated @RequestBody Blog blog) {
        //判断
        //如果blog的id存在则进行编辑 反之进行添加
        Blog temp = null;
        if (blog.getId() != null) {
            //编辑
            temp = blogService.getById(blog.getId());
            //只能编辑自己的文章
            Assert.isTrue(ShiroUtil.getProfile().getId().longValue() == temp.getUserId().longValue(),"没有权限编辑");
        } else {
            //添加
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(DateUtil.now());
            temp.setStatus(0);

        }
        BeanUtil.copyProperties(blog,temp,"id","userId","created","status");
        blogService.saveOrUpdate(temp);
        return Result.success(null);
    }
    /**
     * 删除(未实现)
     */
    @RequiresAuthentication
    @GetMapping("/blog/delete/{id}")
    public Result delete(@PathVariable(name = "id") Long id) {
        boolean blog = blogService.removeById(id);
        Assert.isTrue(blog,"无权限操作");
        return Result.success(blog);
    }
}
