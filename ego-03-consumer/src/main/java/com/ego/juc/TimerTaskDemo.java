package com.ego.juc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * JAVA实现定时任务的几种方式
 * - JDK自带：JDK自带的Timer以及JDK1.5+ 新增的ScheduledExecutorService；
 * - Quartz：简单却强大的JAVA作业调度框架；
 * - Spring3.0：以后自带的Task，可以将它看成一个轻量级的Quartz，而且使用起来比Quartz简单许多；
 *
 * @author liuweiwei
 * @since 2020-11-28
 */
@SuppressWarnings("AlibabaThreadPoolCreation")
public class TimerTaskDemo {
    private static Timer timer;
    private static TimerTask timerTask;
    private static ExecutorService executorService;
    private static ScheduledExecutorService service;
    private static int corePoolSize = 10;

    public static void main(String[] args) throws InterruptedException {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("第一种：设定任务Task指定时间Timer执行时间为2000毫秒");
            }
        };
        timer.schedule(timerTask, 2000);

        timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("第二种：指定任务Task指定时间Timer指定延迟2000固定延迟1000执行");
            }
        };
        timer.schedule(timerTask, 2000, 1000);

        timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("第三种：指定任务Task指定延迟delay固定频率peroid执行");
            }
        };
        timer.scheduleAtFixedRate(timerTask, 2000, 1000);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 28);
        calendar.set(Calendar.SECOND, 50);
        Date time = calendar.getTime();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("第四种：指任务Task指定时间Timer重复固定速率设定将延时每天执行");
            }
        };
        timer.scheduleAtFixedRate(timerTask, time, 1000 * 60 * 60 * 24);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("第五种：定时任务可以用线程的等待来实现");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        executorService = Executors.newCachedThreadPool();
        executorService = Executors.newFixedThreadPool(corePoolSize);
        executorService = Executors.newSingleThreadExecutor();
        executorService = Executors.newScheduledThreadPool(10);

        executorService = Executors.newWorkStealingPool();
        executorService = Executors.newSingleThreadScheduledExecutor();

        service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable, 10, 1, TimeUnit.SECONDS);
    }
}
