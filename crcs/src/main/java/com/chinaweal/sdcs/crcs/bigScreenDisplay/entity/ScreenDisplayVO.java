package com.chinaweal.sdcs.crcs.bigScreenDisplay.entity;

/*领导视角-被投诉主体 TOP10*/

public class ScreenDisplayVO {

    /**
     * 数值
     */
    private Integer value;

    /**
     * 内容
     */
    private String content;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ScreenDisplayVO{" +
                "value=" + value +
                ", content='" + content + '\'' +
                '}';
    }
}
