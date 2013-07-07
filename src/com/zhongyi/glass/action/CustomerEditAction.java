package com.zhongyi.glass.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zhongyi.glass.constant.CodeConstant;
import com.zhongyi.glass.dao.CustomerDao;
import com.zhongyi.glass.form.CustomerForm;
import com.zhongyi.glass.util.CommonUtil;
import com.zhongyi.glass.util.DateUtil;
import com.zhongyi.glass.util.MessageUtil;
import com.zhongyi.glass.util.PropertyUtil;
import com.zhongyi.glass.util.StringUtil;

/**
 * 顾客信息编辑
 * 
 * @author liqianxi
 * @date 2013-06-29
 */
public class CustomerEditAction extends BaseAction {

    /**
     * 顾客信息DAO
     */
    private CustomerDao customerDao;

    @Override
    public ActionForward doLogic(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        CustomerForm customerForm = (CustomerForm) form;

        if (CodeConstant.OPERATE_TYPE_COMMON_EDIT.equals(customerForm.getOperateType())) {
            // 验证输入信息
            if (!checkInput(request, customerForm)) {
                return mapping.findForward("error");
            }

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("NAME", StringUtil.trim(customerForm.getName()));
            param.put("GENDER", customerForm.getGender());
            param.put("MOBILE_PHONE", customerForm.getMobilePhone());
            param.put("HOME_PHONE", customerForm.getHomePhone());
            param.put("DATE", DateUtil.getCurrentDateStr(DateUtil.DATE_FORMATE_YYYYMMDD_WITH_HYPHEN));

            String message = null;
            // 提交信息
            if (StringUtil.isEmpty(customerForm.getId())) {
                // 添加职员信息
                customerDao.insertCustomerInfo(param);
                message = "添加成功！";
            } else {
                // 修改职员信息
                param.put("ID", customerForm.getId());
                customerDao.updateCustomerInfo(param);
                message = "修改成功！";
            }
            // 清空Form
            customerForm.clear();
            MessageUtil.setPopMessageAndRedirect(request, message, "customerView.do");

        } else {
            if (StringUtil.isEmpty(customerForm.getId())) {
                // Menu【增加顾客】时，清空Form
                customerForm.clear();
            } else {
                // 取得待编辑顾客信息
                Map<String, Object> customerInfo = customerDao.getCustomerInfo(customerForm.getId());
                customerForm.setGender(StringUtil.valueOf(customerInfo.get("GENDER")));
                customerForm.setName(StringUtil.valueOf(customerInfo.get("NAME")));
                customerForm.setMobilePhone(StringUtil.valueOf(customerInfo.get("MOBILE_PHONE")));
                customerForm.setHomePhone(StringUtil.valueOf(customerInfo.get("HOME_PHONE")));
            }
        }

        customerForm.setOperateType(null);
        return mapping.findForward("success");
    }

    @Override
    public Class<? extends BaseAction> getClazz() {
        return CustomerEditAction.class;
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
     * 验证输入信息
     * 
     * @param request
     * @param customerForm
     * @return 输入信息是否正确(true:正确/false:有误)
     */
    private boolean checkInput(HttpServletRequest request, CustomerForm customerForm) {
        if (StringUtil.isEmpty(customerForm.getName())) {
            MessageUtil.setPopMessage(request, "姓名不能为空！");
            return false;
        }

        if (StringUtil.isEmpty(customerForm.getGender())) {
            MessageUtil.setPopMessage(request, "性别不能为空！");
            return false;
        }

        String mobilePhone = customerForm.getMobilePhone();
        String homePhone = customerForm.getHomePhone();
        if (StringUtil.isEmpty(mobilePhone) && StringUtil.isEmpty(homePhone)) {
            MessageUtil.setPopMessage(request, "手机号和家庭电话至少留存一项！");
            return false;
        }

        int mobilePhoneLength = StringUtil.getInt(PropertyUtil.get("MOBILE_PHONE_LENGTH"));
        int homePhoneLength = StringUtil.getInt(PropertyUtil.get("HOME_PHONE_LENGTH"));
        if (!StringUtil.isEmpty(mobilePhone)
                && (mobilePhone.length() != mobilePhoneLength || !CommonUtil.isNumeral(mobilePhone))) {
            MessageUtil.setPopMessage(request, "手机号码必须为" + mobilePhoneLength + "位数字！");
            return false;
        }
        if (!StringUtil.isEmpty(homePhone)
                && (homePhone.length() != homePhoneLength || !CommonUtil.isNumeral(homePhone))) {
            MessageUtil.setPopMessage(request, "家庭电话必须为" + homePhoneLength + "位数字！");
            return false;
        }

        return true;
    }
}
