package com.wyl.vueblog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class VueblogApplicationTests {
    private long expire = 604800;
    @Test
    void contextLoads() {
        Date nowDate = new Date();
        System.out.println(nowDate);
        System.out.println(nowDate.getTime());//获得当前时间戳
        System.out.println(nowDate.getTime() * 1000);
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,60);//默认7天过期
        System.out.println(instance.getTime());
        System.out.println(1+1 * 10);
    }

}
