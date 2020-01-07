package com.daxue.IO;



import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileTest {

    @Test
    public void test001(){

        File file01 = new File("./test.txt");

        if (!file01.exists()) {
            try {
                file01.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
