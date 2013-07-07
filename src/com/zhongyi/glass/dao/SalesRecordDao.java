package com.zhongyi.glass.dao;

import java.util.List;
import java.util.Map;

import com.zhongyi.glass.bean.ConcaveCylinderBean;
import com.zhongyi.glass.bean.ConcaveSphereBean;
import com.zhongyi.glass.bean.GoodsTypeBean;
import com.zhongyi.glass.bean.VisualAcuityBean;

/**
 * @Title: SalesRecordDao.java
 * @date 2013-06-29
 * @Description: 销售记录表CRUD操作
 * @author liqianxi
 * 
 */
public class SalesRecordDao extends ZhongyiGlassBaseDao {

    /**
     * 取得视力表信息
     * 
     * @param param 参数
     * @return 视力表信息
     */
    @SuppressWarnings("unchecked")
    public List<VisualAcuityBean> getVisualAcuityList() {
        return zhongyiReadSqlMapClientTemplate.queryForList("zhongyi.getVisualAcuityList");
    }

    /**
     * 取得球镜表信息
     * 
     * @param param 参数
     * @return 球镜表信息
     */
    @SuppressWarnings("unchecked")
    public List<ConcaveSphereBean> getConcaveSphereList() {
        return zhongyiReadSqlMapClientTemplate.queryForList("zhongyi.getConcaveSphereList");
    }

    /**
     * 取得柱镜表信息
     * 
     * @param param 参数
     * @return 柱镜表信息
     */
    @SuppressWarnings("unchecked")
    public List<ConcaveCylinderBean> getConcaveCylinderList() {
        return zhongyiReadSqlMapClientTemplate.queryForList("zhongyi.getConcaveCylinderList");
    }

    /**
     * 取得商品类型表信息
     * 
     * @param param 参数
     * @return 商品类型表信息
     */
    @SuppressWarnings("unchecked")
    public List<GoodsTypeBean> getGoodsTypeList() {
        return zhongyiReadSqlMapClientTemplate.queryForList("zhongyi.getGoodsTypeList");
    }

    /**
     * 取得顾客最近一次的销售信息
     * 
     * @param param 参数
     * @return 顾客最近一次的销售信息
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getLastestSalesRecordList(Map<String, Object> param) {
        return zhongyiReadSqlMapClientTemplate.queryForList("zhongyi.getLastestSalesRecordList", param);
    }

    /**
     * 取得指定顾客的最近一次的销售记录ID
     * 
     * @param id 顾客ID
     * @return 指定顾客的最近一次的销售记录ID
     */
    public String getLastestSalesRecordIdByCustomer(String id) {
        return (String) zhongyiReadSqlMapClientTemplate.queryForObject("zhongyi.getLastestSalesRecordIdByCustomer", id);
    }

    /**
     * 取得指定顾客的历史记录信息
     * 
     * @param id 顾客ID
     * @return 指定顾客的历史记录信息
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getSalesRecordListByCustomer(String id) {
        return zhongyiReadSqlMapClientTemplate.queryForList("zhongyi.getSalesRecordListByCustomer", id);
    }

    /**
     * 取得指定销售记录信息
     * 
     * @param id 销售记录ID
     * @return 指定销售记录信息
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getSalesRecordInfo(String id) {
        return (Map<String, Object>) zhongyiReadSqlMapClientTemplate.queryForObject("zhongyi.getSalesRecordInfo", id);
    }

    /**
     * 添加销售记录
     * 
     * @param param 参数
     * @return
     */
    public Object insertSalesRecordInfo(Map<String, Object> param) {
        return zhongyiWriteSqlMapClientTemplate.insert("zhongyi.insertSalesRecordInfo", param);
    }

    /**
     * 更新销售记录
     * 
     * @param param 参数
     * @return
     */
    public Object updateSalesRecordInfo(Map<String, Object> param) {
        return zhongyiWriteSqlMapClientTemplate.insert("zhongyi.updateSalesRecordInfo", param);
    }

    /**
     * 取得指定销售记录ID对应的商品信息
     * 
     * @param id 销售记录ID
     * @return 指定销售记录ID对应的商品信息
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getSalesGoodsList(String id) {
        return zhongyiReadSqlMapClientTemplate.queryForList("zhongyi.getSalesGoodsList", id);
    }

    /**
     * 添加销售商品
     * 
     * @param param 参数
     * @return
     */
    public Object insertSalesGoodsInfo(Map<String, Object> param) {
        return zhongyiWriteSqlMapClientTemplate.insert("zhongyi.insertSalesGoodsInfo", param);
    }
}
