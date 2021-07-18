package com.ioc.test.test02;

import lombok.extern.slf4j.Slf4j;

/**
 * @author daxue0929
 * @date 2021/07/19
 **/
@Slf4j
public class ConcreateCreator implements Creator{
    @Override
    public <T extends Product> T factory(Class<T> c) {
        Product product = null;
        try {
            product = (Product) Class.forName(c.getName()).newInstance();
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return (T) product;
    }
}
