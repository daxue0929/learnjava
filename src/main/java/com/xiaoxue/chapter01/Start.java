package com.xiaoxue.chapter01;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author daxue0929
 * @date 2020/12/05
 * 高并发解决思路：
 * 扩容、缓存、队列、拆分、服务降级与熔断、数据库切库、分库分表
 **/
@Slf4j
public class Start {

    private static int threadTotal = 200;
    private static int clientTotal = 5000;

    private static int count = 0;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        for (int index = 0; index < clientTotal; index++) {
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
            });
        }
        exec.shutdown();
        log.error("count: {}", count);
    }

    private static void add() {
        count++;
    }
}
