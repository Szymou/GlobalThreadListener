package com.szymou.Controller;

import com.alibaba.fastjson.JSONObject;
import com.szymou.utils.GlobalThreadListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloWorld {
    @RequestMapping("/HelloWorld")
    public String HelloWorld(){
//        ThreadWatchDog watchDog = ThreadWatchDog.getInstance();
//        watchDog.sayHello();
        AThrad aThrad = new AThrad();
        GlobalThreadListener.execFixedThreadPool(aThrad, "我的线程");
        return "HelloWorld!!";
    }

    static class AThrad implements Runnable{
        @Override
        public void run() {
            //将该线程加入队列，用于监控其状态
            int i = 10;
            while (i-- > 0) {
                try {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName());
                } catch (Exception e) {
                    System.out.println("线程出错..." + Thread.currentThread().getName());
                }
            }
        }
    }

    @RequestMapping("/getThreadStatus")
    public JSONObject getThreadStatus(){
        return GlobalThreadListener.getThreadProcee();
    }
}
