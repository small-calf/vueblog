package com.wyl.vueblog.util;/**
 * @Auther:小王
 * @Date:2020/11/11
 * @Description:vueblog
 * @version:1.0
 */

import com.wyl.vueblog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 **/
public class ShiroUtil {
    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();//拿到当前登录的对象
    }
}
