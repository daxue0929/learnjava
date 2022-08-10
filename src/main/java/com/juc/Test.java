package com.juc;

/**
 * @author daxue0929
 * @date 2021/9/12
 */

public class Test {
    public String  id;
    public String  name;
    public String  age;

    public Test(final String id) {
        this.id = id;
    }

    public Test(final String id, final String name) {
        this(id);
        this.name = name;
    }

    public Test(final String id, final String name, final String age) {
        this(id, name);
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public Test setId(final String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Test setName(final String name) {
        this.name = name;
        return this;
    }

    public String getAge() {
        return age;
    }

    public Test setAge(final String age) {
        this.age = age;
        return this;
    }
}
