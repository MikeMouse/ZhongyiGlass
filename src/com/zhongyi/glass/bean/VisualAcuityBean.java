package com.zhongyi.glass.bean;

import java.io.Serializable;

/**
 * 视力表
 * 
 * @author liqianxi
 * @date 2013-06-29
 */
public class VisualAcuityBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1735440700872713418L;

    /**
     * ID
     */
    private String id;

    /**
     * 视力值
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
     * 取得视力值
     * 
     * @return 视力值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设定视力值
     * 
     * @param value 视力值
     */
    public void setValue(String value) {
        this.value = value;
    }
}
