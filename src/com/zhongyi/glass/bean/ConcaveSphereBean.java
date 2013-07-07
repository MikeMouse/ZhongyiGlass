package com.zhongyi.glass.bean;

import java.io.Serializable;

/**
 * 球镜表
 * 
 * @author liqianxi
 * @date 2013-06-29
 */
public class ConcaveSphereBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5236734644357494981L;

    /**
     * ID
     */
    private String id;

    /**
     * 球镜值
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
     * 取得球镜值
     * 
     * @return 球镜值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设定球镜值
     * 
     * @param value 球镜值
     */
    public void setValue(String value) {
        this.value = value;
    }
}
