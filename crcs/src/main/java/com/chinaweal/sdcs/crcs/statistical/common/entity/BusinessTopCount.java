package com.chinaweal.sdcs.crcs.statistical.common.entity;

/**
 * @Author: NaJim
 * @Date: 2019/9/29
 */
public class BusinessTopCount {

    private static final long serialVersionUID = 1L;

    /**
     * 商家名称
     */
    private String subjectname;

    /**
     * 次数统计
     */
    private Integer count;

    public BusinessTopCount() {
    }

    public BusinessTopCount(String subjectname, Integer count) {
        this.subjectname = subjectname;
        this.count = count;
    }

    @Override
    public String toString() {
        return "BusinessTopCount{" +
                "subjectname='" + subjectname + '\'' +
                ", count=" + count +
                '}';
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
