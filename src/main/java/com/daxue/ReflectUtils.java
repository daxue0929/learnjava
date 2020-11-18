package com.daxue;

import com.daxue.model.Apple;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author daxue0929
 * @date 2020/11/19
 * 学习主线：
 **/
public class ReflectUtils {

    public void aboutClass() {
        Apple apple = new Apple(3.99);
        Class<? extends Apple> aClass = apple.getClass();
        try {
            Class<?> appleClass = Class.forName("com.daxue.model.Apple");
            Constructor<?> constructor = appleClass.getConstructor();
            Object instance = constructor.newInstance();
            Field[] fields = appleClass.getFields();
            for (Field item : fields) {
                String name = item.getName();
                Method setPrice = null;
                // 考虑一下，属性是单字符的情况
                if (name.length() != 1) {
                    setPrice = appleClass.getMethod("set" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length()), Double.class);
                }else {
                    setPrice = appleClass.getMethod("set" + name.toUpperCase(), Double.class);
                }
                setPrice.invoke(instance, 12.4);
                Method getPrice = appleClass.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length()));
                System.out.println(getPrice.invoke(instance));
            }

            System.out.println("123");
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
