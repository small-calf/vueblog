package com.wyl.vueblog.shiro;/**
 * @Auther:小王
 * @Date:2020/11/7
 * @Description:vueblog
 * @version:1.0
 */

import cn.hutool.json.JSONUtil;
import com.wyl.vueblog.common.lang.Result;
import com.wyl.vueblog.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器
 **/
@Component
public class jwtFilter extends AuthenticatingFilter {
    @Resource
    private JwtUtils jwtUtils;
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //获取token
        HttpServletRequest req = (HttpServletRequest) request;
        String jwt = req.getHeader("Authorization");
        System.out.println("获得jwt");
        if (StringUtils.isEmpty(jwt)) {
            return null;
        }
        System.out.println(jwt);
        return new JwtToken(jwt);
    }

    //拦截
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //获取token
        HttpServletRequest req = (HttpServletRequest) request;
        String jwt = req.getHeader("Authorization");
        if (StringUtils.isEmpty(jwt)) {
            System.out.println("空");
            return true;
        }else {
            //判断是否过期
            Claims claim = jwtUtils.getClaimByToken(jwt);
            if (claim == null || jwtUtils.isTokenExpired(claim.getExpiration())) {
                System.out.println("失效");
                throw new ExpiredCredentialsException("token已失效，请重新登录");
            }
        }
        return executeLogin(request,response);

    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse resp = (HttpServletResponse) response;
        try {
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            Result result = Result.fail(throwable.getMessage());
            String json = JSONUtil.toJsonStr(result);
            resp.getWriter().println(json);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * 跨域问题
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(org.springframework.http.HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
