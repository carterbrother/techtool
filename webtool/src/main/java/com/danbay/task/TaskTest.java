package com.danbay.task;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by carter on 2017/6/21. Copyright © 2016 －2017 旦倍科技
 *
 * 按照固定频率执行的任务   会反复执行吗？
 *
 */
public class TaskTest {


    public static void main(String[] args) {

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new SimpleTask(),100,200, TimeUnit.MILLISECONDS);


    }

}
