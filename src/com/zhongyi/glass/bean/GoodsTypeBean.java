package com.zhongyi.glass.bean;

import java.io.Serializable;

/**
 * 商品类型表
 * 
 * @author liqianxi
 * @date 2013-06-29
 */
public class GoodsTypeBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6500190783920227677L;

    /**
     * ID
     */
    private String id;

    /**
     * 商品类型名称
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
     * 取得商品类型名称
     * 
     * @return 商品类型名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设定商品类型名称
     * 
     * @param name 商品类型名称
     */
    public void setName(String name) {
        this.name = name;
    }
}
