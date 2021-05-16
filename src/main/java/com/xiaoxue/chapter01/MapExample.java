package com.xiaoxue.chapter01;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author daxue0929
 * @date 2020/12/05
 **/
@Slf4j
public class MapExample {
    private static Map<Integer, Integer> map = Maps.newHashMap();

    private static final int threadNum = 200;
    private static final int clientNum = 5000;

    private static int count = 0;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadNum);
        for (int index = 0; index < clientNum; index++) {
            final int threadNum = index;
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    func(threadNum);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
            });
        }
        exec.shutdown();
        log.error("size: {}", map.size());
    }

    private static void func(int threadNum) {
        map.put(threadNum, threadNum);
    }

}
