package com.myframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author szymou
 * TODO：用缓存？
 * 1.将线程名字、描述写进实体类
 * 2.线程结束，需要对实体类说明已结束。
 * 3.线程池最多10个
 * 4.每20秒循环一次查询实体类状态，判断线程是否结束
 * 5.前端页面显示。
 */
@SpringBootApplication
public class StarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarterApplication.class, args);
    }
}
