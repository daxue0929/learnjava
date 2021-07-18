package com.ioc.test.test02;

import org.apache.poi.ss.formula.functions.T;

/**
 * 抽象工厂
 * @author daxue0929
 * @date 2021/07/19
 **/
public interface Creator {

    /**
     * 工厂方法
     * 创建一个产品抽象,其输入参数类型,可以自行设置
     * @param c
     * @param <T>
     * @return
     */
    public <T extends Product> T factory(Class<T> c);
}
