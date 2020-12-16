package com.myframe.Controller;

import com.myframe.utils.ThreadWatchDog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloWorld {
    @RequestMapping("/HelloWorld")
    public String HelloWorld(){
        ThreadWatchDog watchDog = ThreadWatchDog.getInstance();
        watchDog.sayHello();
        return "HelloWorld!!";
    }
}
