package com.ioc.test.test02;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

/**
 * @author daxue0929
 * @date 2021/07/19
 **/
@Slf4j
public class FactoryMethodDemo {
    public static void main(String[] args){
        Creator creator = new ConcreateCreator();

        //工厂方法模式,实例化了一个电子产品类
        Product electronicProduct = creator.factory(ConcreateProduct.class);
        electronicProduct.make();
    }
}
