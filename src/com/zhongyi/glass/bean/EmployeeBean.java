package com.zhongyi.glass.bean;

import java.io.Serializable;

/**
 * 职员表
 * 
 * @author liqianxi
 * @date 2013-06-29
 */
public class EmployeeBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6922799088066787809L;

    /**
     * ID
     */
    private String id;

    /**
     * 职员姓名
     */
    private String name;

    /**
     * 取得ID
     * 
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设定ID
     * 
     * @param id ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 取得职员姓名
     * 
     * @return 职员姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设定职员姓名
     * 
     * @param name 职员姓名
     */
    public void setName(String name) {
        this.name = name;
    }
}
