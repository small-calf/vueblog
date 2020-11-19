package com.wyl.vueblog.common.lang;/**
 * @Auther:小王
 * @Date:2020/11/4
 * @Description:vueblog
 * @version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {
    private int code;//200:正常
    private String msg;
    private Object data;


    //成功
    public static Result success(Object data) {
        return success(200,"成功",data);
    }

    public static Result success(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
    //失败
    public static Result fail(String msg) {
        return fail(400, msg ,null);
    }
    public static Result fail(String msg, Object data) {
        return fail(400, msg ,data);
    }
    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
