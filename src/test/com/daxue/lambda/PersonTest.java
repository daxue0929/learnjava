package com.daxue.lambda;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Admin
 * @date 2020/03/08
 **/
public class PersonTest {

    public Person person = new Person();


    @Before
    public void setUp() throws Exception {
        System.out.println("start junit:" + System.currentTimeMillis());
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("end  junit: " + System.currentTimeMillis());
    }

    @Test
    public void add() {
        int add = person.add(10, 20);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(30, add);

    }

    @Test
    public void printName() {
        String s = person.printName();
        assertEquals(s, "daxue");

    }
}