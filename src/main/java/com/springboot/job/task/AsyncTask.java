package com.springboot.job.task;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask {
	
	//Threed.sleep是不会释放锁，而wait是释放锁的(对象锁)
	
	@Async
    public Future<Boolean> doTask11() throws Exception {
        long start = System.currentTimeMillis();
        /**
         * (休息1S,阻塞线程) 以验证当前线程对象的机锁被占用时, 是否被可以访问其他同步代码块
         */
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        System.out.println("任务1耗时:" + (end - start) + "毫秒");
        return new AsyncResult<>(true);
    }
	
	@Async
    public Future<Boolean> doTask22() throws Exception {
        long start = System.currentTimeMillis();
        Thread.sleep(700);
        long end = System.currentTimeMillis();
        System.out.println("任务2耗时:" + (end - start) + "毫秒");
        return new AsyncResult<>(true);
    }
    
	@Async
    public Future<Boolean> doTask33() throws Exception {
        long start = System.currentTimeMillis();
        Thread.sleep(600);
        long end = System.currentTimeMillis();
        System.out.println("任务3耗时:" + (end - start) + "毫秒");
        return new AsyncResult<>(true); 
    }

}