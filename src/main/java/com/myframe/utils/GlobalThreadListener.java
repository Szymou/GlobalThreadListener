package com.myframe.utils;

import com.myframe.Entity.ThreadProcess;
import org.apache.tomcat.util.threads.TaskThread;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * GlobalThreadListener.execFixedThreadPool(Runnable Object, "threadName", "线程描述")
 * @date 2020年12月16日 16:56:27
 * @author 熟知宇某
 */
@Component
public class GlobalThreadListener {

    //在此借用一个位置进行创建全局线程池
    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
    private static ThreadPoolExecutor pool = (ThreadPoolExecutor)fixedThreadPool;
    //获取线程池
    public static void execFixedThreadPool(Runnable command, String threadName, String ... attr){
        ThreadProcess threadProcess = new ThreadProcess(
                threadName + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                attr.length > 0 ? attr[0] : "" + Thread.currentThread().getName(),
                "线程等待");
        GlobalThreadListener.getInstance(threadProcess);

        Thread thread = new Thread(() -> {
            Future<?> submit = fixedThreadPool.submit(command);
            while (true){
                if(submit.isDone()){
                    threadProcess.setProcess("线程结束");
                    break;
                }else {
                    if(pool.getLargestPoolSize() - pool.getActiveCount() > 0){
                        threadProcess.setProcess("线程执行");
                    }
                }
            }
        });
        thread.start();

    }

    //线程队列
    private static List<ThreadProcess> threadProcessList = new ArrayList<>();

    //创建实例
    private static GlobalThreadListener instance = new GlobalThreadListener();
    //防止被实例
    private GlobalThreadListener(){}

    //获取唯一的实例
    public static GlobalThreadListener getInstance(ThreadProcess threadProcess){
        threadProcessList.add(threadProcess);
        return instance;
    }

    public static Map<String, List<ThreadProcess>> getThreadProcee(String ... threadName){
        Map<String, List<ThreadProcess>> map = new HashMap<>();
        List<ThreadProcess> runableList = new ArrayList<>();//正在进行的线程
        List<ThreadProcess> wattingList = new ArrayList<>();//正在等待的线程
        List<ThreadProcess> terminatedList = new ArrayList<>();//已终结的线程（当天）
        map.put("【runableList】", runableList);
        map.put("【wattingList】", wattingList);
        map.put("【terminatedList】", terminatedList);

        Iterator<ThreadProcess> processIterator = threadProcessList.iterator();
        while (processIterator.hasNext()){
            ThreadProcess threadProcess = processIterator.next();
            if("线程结束".equals(threadProcess.getProcess())){
                terminatedList.add(threadProcess);
                processIterator.remove();
            }else if("线程等待".equals(threadProcess.getProcess())){
                wattingList.add(threadProcess);
                processIterator.remove();
            }
        }
        runableList.addAll(threadProcessList);

        return map;
    }

























    public void sayHello(){
        System.out.println("你好！熟知宇某！");
    }
}
