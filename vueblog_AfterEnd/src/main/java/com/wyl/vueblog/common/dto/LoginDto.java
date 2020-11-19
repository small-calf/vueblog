package com.wyl.vueblog.common.dto;/**
 * @Auther:小王
 * @Date:2020/11/10
 * @Description:vueblog
 * @version:1.0
 */

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 **/
@Data
public class LoginDto {
    @NotBlank(message = "昵称不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

}
