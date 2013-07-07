package com.zhongyi.glass.dao;

import java.util.List;
import java.util.Map;

import com.zhongyi.glass.bean.EmployeeBean;

/**
 * @Title: EmployeeDao.java
 * @date 2013-06-29
 * @Description: 职员表CRUD操作
 * @author liqianxi
 * 
 */
public class EmployeeDao extends ZhongyiGlassBaseDao {

    /**
     * 取得职员信息总件数
     * 
     * @param param 参数
     * @return 职员信息总件数
     */
    public int getEmployeeCount(Map<String, Object> param) {
        Integer result = (Integer) zhongyiReadSqlMapClientTemplate.queryForObject("zhongyi.getEmployeeCount", param);
        return result == null ? 0 : result.intValue();
    }

    /**
     * 取得职员信息列表（分页）
     * 
     * @param param 参数
     * @return 职员信息列表
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getEmployeeList(Map<String, Object> param, int start, int size) {
        addLimitParams(param, start, size);
        return zhongyiReadSqlMapClientTemplate.queryForList("zhongyi.getEmployeeList", param);
    }

    /**
     * 取得所有有效职员信息
     * 
     * @param param 参数
     * @return 所有有效职员信息
     */
    @SuppressWarnings("unchecked")
    public List<EmployeeBean> getAllEmployeeList() {
        return zhongyiReadSqlMapClientTemplate.queryForList("zhongyi.getAllEmployeeList");
    }

    /**
     * 取得指定职员信息
     * 
     * @param id 职员ID
     * @return 指定职员信息
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getEmployeeInfo(String id) {
        return (Map<String, Object>) zhongyiReadSqlMapClientTemplate.queryForObject("zhongyi.getEmployeeInfo", id);
    }

    /**
     * 添加职员信息
     * 
     * @param param 参数
     * @return
     */
    public Object insertEmployeeInfo(Map<String, Object> param) {
        return zhongyiWriteSqlMapClientTemplate.insert("zhongyi.insertEmployeeInfo", param);
    }

    /**
     * 更新职员信息
     * 
     * @param param 参数
     * @return
     */
    public int updateEmployeeInfo(Map<String, Object> param) {
        return zhongyiWriteSqlMapClientTemplate.update("zhongyi.updateEmployeeInfo", param);
    }
}
