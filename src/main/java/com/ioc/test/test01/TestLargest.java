package com.ioc.test.test01;

import junit.framework.TestCase;

/**
 * @author wangxuedi
 * Date: 2020/4/23 19:23
 * describe:
 */
public class TestLargest extends TestCase {
    public TestLargest(String name) {
        super(name);
    }

    public void testSimple() {
        assertEquals(9, Largest.largest(new int[]{7,8,9}));
    }






}
