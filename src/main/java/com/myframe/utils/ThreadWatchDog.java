package com.myframe.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @date 2020年12月16日 16:56:27
 * @author 熟知宇某
 */
@Component
public class ThreadWatchDog {

    //在此借用一个位置进行创建全局线程池
    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
    //获取线程池
    public static ExecutorService getFixedThreadPool(){
        return fixedThreadPool;
    }

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
