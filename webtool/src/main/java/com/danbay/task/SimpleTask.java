package com.danbay.task;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by carter on 2017/6/21. Copyright © 2016 －2017 旦倍科技
 */
public class SimpleTask extends Thread {

    private AtomicInteger exeCount = new AtomicInteger(0);

    @Override
    public void run() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("任务执行完毕");
        exeCount.incrementAndGet();


    }
}
