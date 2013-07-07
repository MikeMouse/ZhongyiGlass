/*
 * Copyright (C) 2012 mAPPn.Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhongyi.glass.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 * @Title: ZhongyiGlassBaseDao.java
 * @date 2013-06-29
 * @Description: 中义眼镜Dao基类
 * @author liqianxi
 * 
 */
public class ZhongyiGlassBaseDao {

    static final String PAGE_LIMIT = "pageLimit";
    static final String PAGE_START = "pageStart";
    static final String CONDITION = "condition";
    static final String SEARCH_TEXT = "searchText";

    SqlMapClientTemplate zhongyiReadSqlMapClientTemplate;
    SqlMapClientTemplate zhongyiWriteSqlMapClientTemplate;

    Object queryForObject(String statementName) {
        return zhongyiReadSqlMapClientTemplate.queryForObject(statementName);
    }

    Object queryForObject(String statementName, Object parameterObject) {
        return zhongyiReadSqlMapClientTemplate.queryForObject(statementName,
                parameterObject);
    }

    @SuppressWarnings("rawtypes")
    List queryForList(String statementName, Object parameterObject) {
        return zhongyiReadSqlMapClientTemplate.queryForList(statementName,
                parameterObject);
    }

    @SuppressWarnings("rawtypes")
    List queryForList(String statementName) {
        return zhongyiReadSqlMapClientTemplate.queryForList(statementName);
    }

    Object insert(String statementName, Object parameterObject) {
        return zhongyiWriteSqlMapClientTemplate.insert(statementName,
                parameterObject);
    }

    int delete(String statementName, Object parameterObject) {
        return zhongyiWriteSqlMapClientTemplate.delete(statementName,
                parameterObject);
    }

    int update(String statementName, Object parameterObject) {
        return zhongyiWriteSqlMapClientTemplate.update(statementName,
                parameterObject);
    }

    /**
     * 设置分页参数
     * 
     * @param param 参数
     * @param skipResults 跳过件数
     * @param maxResults 取得记录件数
     * @return 分页参数Map
     */
    protected Map<String, Object> addLimitParams(Map<String, Object> param,
            int skipResults, int maxResults) {
        if (param == null) {
            param = new HashMap<String, Object>(4);
        }
        param.put(PAGE_START, skipResults);
        param.put(PAGE_LIMIT, maxResults);
        return param;
    }

    public void setZhongyiReadSqlMapClientTemplate(
            SqlMapClientTemplate zhongyiReadSqlMapClientTemplate) {
        this.zhongyiReadSqlMapClientTemplate = zhongyiReadSqlMapClientTemplate;
    }

    public void setZhongyiWriteSqlMapClientTemplate(
            SqlMapClientTemplate zhongyiWriteSqlMapClientTemplate) {
        this.zhongyiWriteSqlMapClientTemplate = zhongyiWriteSqlMapClientTemplate;
    }
}
