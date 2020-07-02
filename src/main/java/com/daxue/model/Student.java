package com.daxue.model;

//import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @author daxue0929
 * @date 2020/03/11
 **/
public class Student {

//    @Excel(name = "id", orderNum = "1")
    private Integer id;

//    @Excel(name = "name", orderNum = "2")
    private String name;

//    @Excel(name = "sex", orderNum = "3")
    private String sex;

//    @Excel(name = "cla", orderNum = "4")
    private String cla;

    public Student() {
    }

    public Student(Integer id, String name, String sex, String cla) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.cla = cla;
    }
//    public Student(String id, String name, String sex, String cla) {
//        this.id = Integer.parseInt(id);
//        this.name = name;
//        this.sex = sex;
//        this.cla = cla;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setId(String id) { Integer.parseInt(id); }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCla() {
        return cla;
    }

    public void setCla(String cla) {
        this.cla = cla;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", cla='" + cla + '\'' +
                '}';
    }
}
