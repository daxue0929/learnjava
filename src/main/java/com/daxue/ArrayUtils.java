package com.daxue;

import com.sun.org.apache.xpath.internal.operations.String;

import java.util.*;

/**
 * @author daxue0929
 * @date 2020/11/19
 * 学习主线：
 * Arrays
 * Collections
 *
 **/
public class ArrayUtils {

    /**
     * 声明数组的方式。
     * 数组转为list、list转为数组。
     */
    public void declarativeWay () {

        // 数组类型[] 数组名=new 数组类型[数组长度];
        Integer[] array = new Integer[15];

        Arrays.fill(array, 0);

        // 数组类型[] 数组名={数组0,数组1,数组2,数组3,......};
        Integer[] array2 = {1, 2, 3};

        // 返回的是Arrays的内部类 Arrays.ArrayList 继承自AbstractList
        // 不支持 add 和 remove方法
        List<Integer> integerList = Arrays.asList(array2);

        ArrayList<Integer> list = new ArrayList<>(integerList);
        list.add(4);

        // List中有两个方法toArray， 穿个数组参数，返回数组的类型。
        String[] strings = list.toArray(new String[list.size()]);

        Map<String, Integer> map = Collections.EMPTY_MAP;

    }



}
