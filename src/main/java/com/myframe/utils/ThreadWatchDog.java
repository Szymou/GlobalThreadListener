package com.myframe.utils;

import org.springframework.stereotype.Component;

/**
 * @date 2020年12月16日 16:56:27
 * @author 熟知宇某
 */
@Component
public class ThreadWatchDog {

    //创建实例
    private static ThreadWatchDog instance = new ThreadWatchDog();
    //防止被实例
    private ThreadWatchDog(){}

    //获取唯一的实例
    public static ThreadWatchDog getInstance(){
        return instance;
    }

    public void sayHello(){
        System.out.println("你好！熟知宇某！");
    }
}
