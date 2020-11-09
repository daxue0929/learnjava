package com.test2;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Consumer;

public class NullExpection {

    public NullExpection instance;

    @Before
    public void before() {
        instance = new NullExpection();
    }

    @Test
    public void test() {
        Object aNull = instance.isNull(null);
        System.out.println("" + aNull);
    }

    @Test
    public void test2() {

    }

    @Test
    public void test1() {
        ThreadGroup firstGroup = new ThreadGroup("一号组");

        Thread thread = new Thread(firstGroup, "线程1");
        Thread thread2 = new Thread(firstGroup, "线程2");
        Thread thread3 = new Thread(firstGroup, "线程3");
        Thread thread4 = new Thread(firstGroup, "线程4");
        thread.start();
        System.out.println(thread.getId() + ":" + thread.getName());
        System.out.println("活动的线程数：" + thread.getThreadGroup().activeCount());
        thread.stop();
    }


    @Test
    public void testThread() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Integer count = 0;
                while (true) {
                    System.out.println("----Thread-1: " + ++count);
                }

            }
        };

        runnable.run();
    }

    public  Object isNull(Object obj) {
        if (Optional.ofNullable(obj).isPresent()) {
            return obj;
        }
        Optional<Object> empty = Optional.empty(); // &#x8FD4;&#x56DE;&#x4E00;&#x4E2A;&#x7A7A;&#x503C;&#x7684;Optional&#x5BF9;&#x8C61;
        empty.ifPresent((Consumer) o -> {

        });

        return new Object();
    }


}
