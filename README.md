# GlobalThreadLister
 *监听全局线程状态*
> ### 目标
> 1. 【未实现】开发者自定义线程池大小（目前固定为8）
>   > private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);  
>   > 懒加载方式可实现
> 2. 【已实现】简便将Runnable实现类倒入线程池，并进行监听
>   > **GlobalThreadListener.execFixedThreadPool**(Runnable run, "线程名称", "描述(可有可无)");
> 3. 【已实现】简便获取在该线程池的各个子线程状态
>   > **GlobalThreadListener.getThreadProcee**();    
>   > **return**：{"【wattingList】":[ ],"【runableList】":[ ],"【terminatedList】":[ ]}
> 4. 【未实现】弹小窗（js实现？vue写插件实现？）
>   > 鼠标划过，伪类hover触发
> 5. 【即将实现】根据线程名称模糊查询线程状态
>   > 管理员输入大致被调度的任务，可查询其状态。
> 6. 【即将实现】自定义线程状态刷新率
>   > 不间断刷新将使得系统负载较大，所以必须要有自定义刷新时间参数，开发者灵活应用
> 7. 待续

> ### 当前不足
> 1. 一个子线程要配合一个监控线程，才能监控该子线程的状态
>   > 因为实现Runnable，所以没有返回值。  
>   >为解决，另起一个线程：利用实例的引用的内存共享机制，来代表Runnable进度
> 2. 监控子线程状态，粒度太大
>   > **Future<?> submit = fixedThreadPool.submit(new Runnable);**  
>   > submit.**isDone()**
> 3. 等待状态和执行状态会有误差，取数算法太简略
>   > 一个实例绑定一个子线程，子线程结束，实例（实体类）属性不一定紧接着就更新。

> ### 使用说明
> 1. 实现一个线程
>   > TestRunnable testRunnable = new TestRunnable(name);//TestRunnable 继承Runnable
> 2. 线程倒入线程池
>   > GlobalThreadListener.execFixedThreadPool(testRunnable, “线程名”, "描述");//将线程执行与线程池，该线程被监听
> 3. 获取线程池各个线程状态
>   > GlobalThreadListener.getThreadProcee();//返回GlobalThreadListener监听的所有线程详情（包括等待、执行、终止状态）  
>   > GlobalThreadListener.getThreadProceeStatusNum();//返回GlobalThreadListener监听的所有线程详情（包括等待、执行、终止状态）
>### 逻辑
> 暂不提供，看代码可懂