package com.daxue;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IOUtilsTest extends TestCase {

    public IOUtils instance = null;

    @Before
    public void setUp() throws Exception {
        instance = new IOUtils();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testReadStrFromFileInputStream() {
        instance.readStrFromFileInputStream();
    }

    public void testReadFromStringBufferInputStream() {
    }

    public void testReadStrFromByteArrayInputStream() {
        instance.readStrFromByteArrayInputStream();

    }
}
