package com.juc;

import com.google.common.util.concurrent.AtomicDouble;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author daxue0929
 * @date 2021/9/12
 *
 * juc并发包下的一些需要深入掌握的类
 *
 */

public class ReadMe {
    /**
     * atomic:
     *  AtomicInteger
     *  AtomicDouble
     *  AtomicBoolean
     *  AtomicLong
     *  AtomicReference
     *  LongAccumulator
     *
     *
     * lock
     *  AQS
     *  LOCK
     *  Condtion
     *  LOCKSupport
     *  ReadWriteLock
     *  ReentrantLock
     *
     *  CountDownLatch
     *  CyclicBarrier
     *  Semaphore
     *  FutureTask
     *  Callable
     *
     *
     *  ConcurrentHashMap
     *  CopyOnWriteArrayList
     *  BlockingQueue
     *  BlockingDeque
     *
     *
     *  Executor
     *
     */
    public static void main(String[] args) {

        CopyOnWriteArrayList arrayList = new CopyOnWriteArrayList();
        AtomicInteger atomicInteger = new AtomicInteger();
        AtomicDouble atomicDouble = new AtomicDouble();
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        AtomicLong atomicLong = new AtomicLong();
        AtomicReference<String> stringAtomicReference = new AtomicReference<>();
        //LongAccumulator longAccumulator = new LongAccumulator();


    }



}
