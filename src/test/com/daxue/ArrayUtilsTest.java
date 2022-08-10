package com.daxue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author daxue0929
 * @date 2020/11/19
 **/
public class ArrayUtilsTest {

    public ArrayUtils instance = null;

    @Before
    public void setUp() throws Exception {
        instance = new ArrayUtils();
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void declarativeWay() {
        instance.declarativeWay();

    }
}
