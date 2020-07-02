package com.daxue.model;

//import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @author daxue0929
 * @date 2020/03/11
 **/
public class Caurse {

//    @Excel(name = "id", orderNum = "1")
    private String id;

//    @Excel(name = "name", orderNum = "2")
    private String name;

//    @Excel(name = "credit", orderNum = "3")
    private String credit;

    public Caurse() {
    }

    public Caurse(String id, String name, String credit) {
        this.id = id;
        this.name = name;
        this.credit = credit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Caurse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", credit='" + credit + '\'' +
                '}';
    }
}
