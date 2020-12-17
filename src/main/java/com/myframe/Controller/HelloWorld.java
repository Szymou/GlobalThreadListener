package com.myframe.Controller;

import com.myframe.Entity.ThreadProcess;
import com.myframe.utils.GlobalThreadListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

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
                    System.out.println("AThread..." + Thread.currentThread().getName());
                } catch (Exception e) {
                    System.out.println("线程出错..." + Thread.currentThread().getName());
                }
            }
        }
    }

    @RequestMapping("/getThreadStatus")
    public Map<String, List<ThreadProcess>> getThreadStatus(){
        return GlobalThreadListener.getThreadProcee();
    }
}
