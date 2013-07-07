package com.zhongyi.glass.bean;

import java.io.Serializable;

/**
 * 柱镜表
 * 
 * @author liqianxi
 * @date 2013-06-29
 */
public class ConcaveCylinderBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4550148119176819682L;

    /**
     * ID
     */
    private String id;

    /**
     * 柱镜值
     */
    private String value;

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
     * 取得柱镜值
     * 
     * @return 柱镜值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设定柱镜值
     * 
     * @param value 柱镜值
     */
    public void setValue(String value) {
        this.value = value;
    }
}
