package com.chinaweal.sdcs.crcs.bigScreenDisplay.entity;

/*领导视角*/

public class DoubleVO {

    /**
     * 数值
     */
    private String name;

    /**
     * 内容
     */
    private Double value;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DoubleVO{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
