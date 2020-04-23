package com.daxue.str;

public enum EnumStatus {

    STATUS1("待审核", 1), STATUS2("绿色", 2), STATUS3("绿色", 2), STATUS4("绿色", 2);

    private String name;
    private int index;

    // 构造方法
    private EnumStatus(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (EnumStatus c : EnumStatus.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
