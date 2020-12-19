package com.szymou.Controller;

import top.szymou.utils.GlobalThreadListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class HelloWorld {
    private int i = 0;
    @RequestMapping("/HelloWorld")
    public String HelloWorld(){
        AThrad aThrad = new AThrad();
        GlobalThreadListener globalThreadListener = GlobalThreadListener.getInstance();
        globalThreadListener.execFixedThreadPool(aThrad, "线程" + i++, "描述" + i);
        return "线程" + i;
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
    public Map<String, Object> getThreadStatus(){
        return GlobalThreadListener.getThreadProcee();
    }

    @RequestMapping("/getThreadProceeStatusNum")
    public Map<String, Object> getThreadProceeStatusNum(){
        return GlobalThreadListener.getThreadProceeStatusNum();
    }
}
