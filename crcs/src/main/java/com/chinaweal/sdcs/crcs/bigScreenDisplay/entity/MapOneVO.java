package com.chinaweal.sdcs.crcs.bigScreenDisplay.entity;

/*领导视角-被投诉主体 TOP10*/

public class MapOneVO {

    /**
     * 数值
     */
    private String name;

    /**
     * 内容
     */
    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MapOneVO{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
