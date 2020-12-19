package top.szymou.utils;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * GlobalThreadListener.execFixedThreadPool(Runnable Object, "threadName", "线程描述")
 *
 * @author 熟知宇某
 * @date 2020年12月16日 16:56:27
 */
public class GlobalThreadListener {

    //线程队列
    private static List<ThreadProperties> threadPropertiesList = new ArrayList<>();

    //创建实例
    private static GlobalThreadListener instance = new GlobalThreadListener();

    //防止被实例
    private GlobalThreadListener() {
    }

    //获取唯一的实例
    public static GlobalThreadListener getInstance(ThreadProperties threadProperties) {
        threadPropertiesList.add(threadProperties);
        return instance;
    }

    //创建全局线程池
    private static final int poolSize = 8;
    private static int activityCount = 0;
    private static int extendPoolSize = poolSize;
    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(poolSize);
    private static ThreadPoolExecutor pool = (ThreadPoolExecutor)fixedThreadPool;

    //线程池线程状态列表
    private static Set<ThreadProperties> terminatedList = new HashSet<>();//已终结的线程（当天）
    private static List<ThreadProperties> runnableList;//正在等待的线程
    private static List<ThreadProperties> wattingList;//正在等待的线程


    //开发者直接调用--执行线程
    public static void execFixedThreadPool(Runnable command, String threadName, String... attr) {
        ThreadProperties threadProperties = new ThreadProperties(
                threadName,
                new Date(),
                attr.length > 0 ? attr[0] : "",
                ProcessStatus.WAITTING.getCode());
        //加入线程队列
        GlobalThreadListener.getInstance(threadProperties);
        //开启线程监听每个子线程的状态
        ListenProcessStatus listenProcessStatus = new ListenProcessStatus(threadProperties, command);
        Thread thread = new Thread(listenProcessStatus);
        thread.start();

    }

    //开发者直接调用，获取线程池状态--详情
    public static Map<String, Object> getThreadProcee(String... threadName) {
        handleProcessStatus();
        Map<String, Object> map = new HashMap<>();
        map.put("【wattingList】", wattingList);
        map.put("【runableList】", runnableList);
        map.put("【terminatedList】", terminatedList);
        return map;
    }

    //开发者直接调用，获取线程池状态--数量
    public static Map<String, Object> getThreadProceeStatusNum(String... threadName) {
        handleProcessStatus();
        Map<String, Object> map = new HashMap<>();
        map.put("【wattingList】", wattingList.size());
        map.put("【runableList】", runnableList.size());
        map.put("【terminatedList】", terminatedList.size());
        return map;
    }


    /**
     * ------------------------------------------------------------------以下为封装函数-----------------------------------------------------------------
     **/

    //【监听】每个线程的状态
    private static class ListenProcessStatus implements Runnable {
        private ThreadProperties threadProperties;
        private Runnable command;

        ListenProcessStatus(ThreadProperties threadProperties, Runnable command) {
            this.threadProperties = threadProperties;
            this.command = command;
        }

        @Override
        public void run() {
            //设置刷新状态时间(s)
            int refresh = 0;
            Future<?> submit = fixedThreadPool.submit(command);
            while (true) {
                synchronized (this) {
                    threadProperties.setProcess(ProcessStatus.RUNNING.getCode());
                    if (submit.isDone()) {
                        threadProperties.setProcess(ProcessStatus.TERMINATED.getCode());
                        activityCount--;
                        break;
                    }
                }
            }
        }
    }

    //【处理】每个线程的状态
    private static void handleProcessStatus() {
        wattingList = new ArrayList<>();//正在等待的线程
        runnableList = new ArrayList<>();//正在等待的线程
        Iterator<ThreadProperties> processIterator = threadPropertiesList.iterator();
        while (processIterator.hasNext()) {
            ThreadProperties threadProperties = processIterator.next();
            if (threadProperties.getProcess() == ProcessStatus.TERMINATED.getCode()) {
                terminatedList.add(threadProperties);
                processIterator.remove();
            }
//            else if (threadProperties.getProcess() == ProcessStatus.WAITTING.getCode()) {
//                wattingList.add(threadProperties);
//            }
            else {
                wattingList.add(threadProperties);
            }
        }

        //粗略取“正在等待”的线程
        Iterator<ThreadProperties> wattingIterator = wattingList.iterator();
        Integer i = 0;
        while (wattingIterator.hasNext()){
            ThreadProperties threadProperties = wattingIterator.next();
            if(i < poolSize){
                runnableList.add(threadProperties);
                wattingIterator.remove();
                i++;
            }
        }

    }


    public void sayHello() {
        System.out.println("你好！熟知宇某！");
    }
}
