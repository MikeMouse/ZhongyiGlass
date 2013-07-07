package com.zhongyi.glass.dao;

import java.util.List;
import java.util.Map;

/**
 * @Title: CustomerDAO.java
 * @date 2013-06-29
 * @Description:顾客表CRUD操作
 * @author liqianxi
 * 
 */
public class CustomerDao extends ZhongyiGlassBaseDao {

    /**
     * 取得顾客信息总件数
     * 
     * @param param 参数
     * @return 顾客信息总件数
     */
    public int getCustomerCount(Map<String, Object> param) {
        Integer result = (Integer) zhongyiReadSqlMapClientTemplate.queryForObject("zhongyi.getCustomerCount", param);
        return result == null ? 0 : result.intValue();
    }

    /**
     * 取得顾客信息列表
     * 
     * @param param 参数
     * @return 顾客信息列表
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getCustomerList(Map<String, Object> param, int start, int size) {
        addLimitParams(param, start, size);
        return zhongyiReadSqlMapClientTemplate.queryForList("zhongyi.getCustomerList", param);
    }

    /**
     * 取得指定顾客信息
     * 
     * @param param 参数
     * @return 指定顾客信息
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getCustomerInfo(String id) {
        return (Map<String, Object>) zhongyiReadSqlMapClientTemplate.queryForObject("zhongyi.getCustomerInfo", id);
    }

    /**
     * 添加顾客信息
     * 
     * @param param 参数
     * @return 顾客信息
     */
    public Object insertCustomerInfo(Map<String, Object> param) {
        return zhongyiWriteSqlMapClientTemplate.insert("zhongyi.insertCustomerInfo", param);
    }

    /**
     * 更新顾客信息
     * 
     * @param param 参数
     * @return 顾客信息
     */
    public int updateCustomerInfo(Map<String, Object> param) {
        return zhongyiWriteSqlMapClientTemplate.update("zhongyi.updateCustomerInfo", param);
    }
}
