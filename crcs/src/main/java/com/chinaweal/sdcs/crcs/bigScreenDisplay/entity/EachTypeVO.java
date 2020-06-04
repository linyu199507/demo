package com.chinaweal.sdcs.crcs.bigScreenDisplay.entity;

/*领导视角-近一个月的业务量 */

public class EachTypeVO {

    /**
     * 品类
     */
    private String type;

    /**
     * 值
     */
    private Integer value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "EachTypeVO{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
