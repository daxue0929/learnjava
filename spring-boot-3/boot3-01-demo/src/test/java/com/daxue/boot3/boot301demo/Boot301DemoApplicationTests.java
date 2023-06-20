package com.daxue.boot3.boot301demo;



import com.daxue.boot3.boot301demo.controller.HelloController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
//@ExtendWith(SpringExtension.class)
class Boot301DemoApplicationTests {

    @BeforeAll
    public void classInit() {

    }


    @BeforeEach
    public void init() {

    }

    @Test
    void contextLoads() {
    }


    @Test
    public void TestA(){
//        assertEquals(12, 12);
        Assertions.assertEquals("1", "1");

//        Assertions.assertNotNull();
    }
}
