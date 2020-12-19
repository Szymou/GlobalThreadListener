# GlobalThreadLister
 *监听全局线程情况*
> ### 目标
> 1. 【未实现】开发者自定义线程池大小（目前固定为5）
>   > private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
> 2. 【已实现】简便将Runnable实现类倒入线程池，并进行监听
>   > **GlobalThreadListener.execFixedThreadPool**(Runnable run, "线程名称", "描述(可有可无)");
> 3. 【已实现】简便获取在该线程池的各个子线程状态
>   > **GlobalThreadListener.getThreadProcee**();    
>   > **return**：{"【wattingList】":[ ],"【runableList】":[ ],"【terminatedList】":[ ]}
> 4. 【未实现】弹小窗（js实现？vue写插件实现？）
> 5. 【未实现】

>### 逻辑
> 没有visio，先不提供，看代码可以看懂