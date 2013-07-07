package com.zhongyi.glass.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zhongyi.glass.constant.CodeConstant;
import com.zhongyi.glass.dao.SalesRecordDao;
import com.zhongyi.glass.form.SalesRecordForm;
import com.zhongyi.glass.util.CommonUtil;
import com.zhongyi.glass.util.MessageUtil;
import com.zhongyi.glass.util.StringUtil;

/**
 * 销售记录查看
 * 
 * @author liqianxi
 * @date 2013-06-29
 */
public class SalesRecordViewAction extends BaseAction {

    /**
     * 销售记录DAO
     */
    private SalesRecordDao salesRecordDao;

    @Override
    public ActionForward doLogic(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        SalesRecordForm salesRecordForm = (SalesRecordForm) form;
        // 详细列表
        List<Map<String, Object>> detailList = null;
        if (CodeConstant.OPERATE_TYPE_COMMON_SEARCH.equals(salesRecordForm.getOperateType())) {
            // 查询顾客最近一次消费记录
            Map<String, Object> param = new HashMap<String, Object>();
            // 顾客姓名
            param.put("CUSTOMER_NAME", salesRecordForm.getCustomerName());
            // 移动电话
            param.put("MOBILE_PHONE", salesRecordForm.getMobilePhone());
            // 家庭电话
            param.put("HOME_PHONE", salesRecordForm.getHomePhone());
            detailList = salesRecordDao.getLastestSalesRecordList(param);
            editDetailList(detailList);
            if (detailList == null || detailList.size() == 0) {
                MessageUtil.setPopMessage(request, "没有对应的销售记录！");
            }
        } else {
            salesRecordForm.clear();
        }

        salesRecordForm.setSearchResultList(detailList);
        salesRecordForm.setOperateType(null);
        return mapping.findForward("success");
    }

    @Override
    public Class<? extends BaseAction> getClazz() {
        return SalesRecordViewAction.class;
    }

    /**
     * 取得销售记录DAO
     * 
     * @return 销售记录DAO
     */
    public SalesRecordDao getSalesRecordDao() {
        return salesRecordDao;
    }

    /**
     * 设定销售记录DAO
     * 
     * @param SalesRecordDao 销售记录DAO
     */
    public void setSalesRecordDao(SalesRecordDao salesRecordDao) {
        this.salesRecordDao = salesRecordDao;
    }

    /**
     * 编辑详细列表记录信息
     * 
     * @param detailList 详细列表记录信息
     */
    private void editDetailList(List<Map<String, Object>> detailList) {
        if (detailList != null && detailList.size() > 0) {
            for (Map<String, Object> item : detailList) {
                long totalAmount = StringUtil.getLong(StringUtil.valueOf(item.get("TOTAL_AMOUNT")));
                long totalDiscount= StringUtil.getLong(StringUtil.valueOf(item.get("TOTAL_DISCOUNT")));
                item.put("LATEST_EXPENSE", CommonUtil.formatDisplayMoney(totalAmount - totalDiscount));
            }
        }
    }

}
