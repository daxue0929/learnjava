package com.ioc.test.test02;

import lombok.extern.slf4j.Slf4j;

/**
 * 电子产品生产类
 * @author daxue0929
 * @date 2021/07/19
 **/
@Slf4j
public class ConcreateProduct implements Product{
    @Override
    public void make() {
        log.info("制造了电子一个产品");
    }

    @Override
    public void use() {
        log.info("使用了电子一个产品");
    }
}
