package com.zhongyi.glass.form;

import java.util.List;
import java.util.Map;

/**
 * 顾客信息Form
 * 
 * @author liqianxi
 * @date 2013-06-29
 */
public class CustomerForm extends BasePageForm {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -959210865940937646L;

    /**
     * 顾客ID
     */
    private String id;


    /**
     * 顾客姓名
     */
    private String name;


    /**
     * 顾客性别
     */
    private String gender;


    /**
     * 顾客手机
     */
    private String mobilePhone;


    /**
     * 顾客家庭电话
     */
    private String homePhone;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 详细列表
     */
    private List<Map<String, Object>> detailList = null;

    /**
     * 取得顾客ID
     * 
     * @return 顾客ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设定顾客ID
     * 
     * @param name 顾客ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 取得顾客姓名
     * 
     * @return 顾客姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设定顾客姓名
     * 
     * @param name 顾客姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 取得顾客性别
     * 
     * @return 顾客性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设定顾客性别
     * 
     * @param gender 顾客性别
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 取得顾客手机
     * 
     * @return 顾客手机
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设定顾客手机
     * 
     * @param mobilePhone 顾客手机
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * 取得顾客家庭电话
     * 
     * @return 顾客家庭电话
     */
    public String getHomePhone() {
        return homePhone;
    }

    /**
     * 设定顾客家庭电话
     * 
     * @param homePhone 顾客家庭电话
     */
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
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
        this.mobilePhone = null;
        this.homePhone = null;
        this.operateType = null;
        this.detailList = null;
    }
}
