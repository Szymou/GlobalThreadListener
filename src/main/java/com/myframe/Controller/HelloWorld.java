package com.myframe.Controller;

import com.myframe.utils.ThreadWatchDog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;

@RestController
@RequestMapping("/test")
public class HelloWorld {
    @RequestMapping("/HelloWorld")
    public String HelloWorld(){
        ThreadWatchDog watchDog = ThreadWatchDog.getInstance();
        watchDog.sayHello();
        AThrad aThrad = new AThrad();
        ExecutorService fixedThreadPool = ThreadWatchDog.getFixedThreadPool();
        fixedThreadPool.execute(aThrad);
        return "HelloWorld!!";
    }

    static class AThrad implements Runnable{
        @Override
        public void run() {

        }
    }
}
