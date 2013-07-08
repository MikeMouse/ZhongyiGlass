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
import com.zhongyi.glass.dao.CustomerDao;
import com.zhongyi.glass.form.CustomerForm;
import com.zhongyi.glass.page.Page;
import com.zhongyi.glass.util.StringUtil;

/**
 * 顾客信息查看
 * 
 * @author liqianxi
 * @date 2013-06-29
 */
public class CustomerViewAction extends BaseAction {

    /**
     * 顾客信息DAO
     */
    private CustomerDao customerDao;

    @Override
    public ActionForward doLogic(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        CustomerForm customerForm = (CustomerForm) form;

        Map<String, Object> param = new HashMap<String, Object>();
        int count = 0;
        // 详细列表
        List<Map<String, Object>> detailList = null;
        if (CodeConstant.OPERATE_TYPE_COMMON_SEARCH.equals(customerForm.getOperateType())) {
            param.put("NAME", StringUtil.trim(customerForm.getName()));
            param.put("MOBILE_PHONE", StringUtil.trim(customerForm.getMobilePhone()));
            param.put("HOME_PHONE", StringUtil.trim(customerForm.getHomePhone()));
        }

        count = customerDao.getCustomerCount(param);
        int nowPage = customerForm.getNowPage();
        Page pageInfo = new Page(nowPage, count);
        if (count > 0) {
            detailList = customerDao.getCustomerList(param, pageInfo.getRecordStart() - 1, pageInfo.getPageSize());
        }

        // 编辑详细列表记录信息
        editDetailList(detailList);
        customerForm.setDetailList(detailList);
        customerForm.setOperateType(null);
        request.setAttribute("pageInfo", pageInfo);
        return mapping.findForward("success");
    }

    @Override
    public Class<? extends BaseAction> getClazz() {
        return CustomerViewAction.class;
    }

    /**
     * 取得顾客信息DAO
     * 
     * @return 顾客信息DAO
     */
    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    /**
     * 设定顾客信息DAO
     * 
     * @param customerDao 顾客信息DAO
     */
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * 编辑详细列表记录信息
     * 
     * @param detailList 详细列表记录信息
     */
    private void editDetailList(List<Map<String, Object>> detailList) {
        if (detailList != null && detailList.size() > 0) {
            for (Map<String, Object> item : detailList) {
                // 职员性别
                if (CodeConstant.GENDER_FEMALE.equals(StringUtil.valueOf(item.get("GENDER")))) {
                    item.put("GENDER", CodeConstant.GENDER_DISPLAY_FEMALE);
                } else if (CodeConstant.GENDER_MALE.equals(StringUtil.valueOf(item.get("GENDER")))) {
                    item.put("GENDER", CodeConstant.GENDER_DISPLAY_MALE);
                }
            }
        }
    }
}
