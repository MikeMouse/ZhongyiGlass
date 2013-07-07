package com.zhongyi.glass.form;

import java.util.List;
import java.util.Map;

/**
 * 职员信息Form
 * 
 * @author liqianxi
 * @date 2013-06-29
 */
public class EmployeeForm extends BasePageForm {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6118985146863877823L;

    /**
     * 职员ID
     */
    private String id;


    /**
     * 职员姓名
     */
    private String name;


    /**
     * 职员性别
     */
    private String gender;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 详细列表
     */
    private List<Map<String, Object>> detailList = null;

    /**
     * 取得职员ID
     * 
     * @return 职员ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设定职员ID
     * 
     * @param name 职员ID
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

    /**
     * 取得职员性别
     * 
     * @return 职员性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设定职员性别
     * 
     * @param name 职员性别
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 取得操作类型
     * 
     * @return 操作类型
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设定操作类型
     * 
     * @param operateType 操作类型
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 取得详细列表
     * 
     * @return 详细列表
     */
    public List<Map<String, Object>> getDetailList() {
        return detailList;
    }

    /**
     * 设定详细列表
     * 
     * @param detailList 详细列表
     */
    public void setDetailList(List<Map<String, Object>> detailList) {
        this.detailList = detailList;
    }

    /**
     * 清空Form
     */
    @Override
    public void clear() {
        super.clear();
        this.id = null;
        this.name = null;
        this.gender = null;
        this.operateType = null;
        this.detailList = null;
    }
}
