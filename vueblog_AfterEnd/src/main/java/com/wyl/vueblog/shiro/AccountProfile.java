package com.wyl.vueblog.shiro;/**
 * @Auther:小王
 * @Date:2020/11/8
 * @Description:vueblog
 * @version:1.0
 */

import lombok.Data;

import java.io.Serializable;

/**
 **/
@Data
public class AccountProfile implements Serializable {

    private Long id;

    private String username;

    private String avatar;

    private String email;
}
