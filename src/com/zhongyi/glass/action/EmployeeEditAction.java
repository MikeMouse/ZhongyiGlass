package com.zhongyi.glass.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zhongyi.glass.constant.CodeConstant;
import com.zhongyi.glass.dao.EmployeeDao;
import com.zhongyi.glass.form.EmployeeForm;
import com.zhongyi.glass.util.DateUtil;
import com.zhongyi.glass.util.MessageUtil;
import com.zhongyi.glass.util.StringUtil;

/**
 * 职员信息编辑
 * 
 * @author liqianxi
 * @date 2013-06-29
 */
public class EmployeeEditAction extends BaseAction {

    /**
     * 职员信息DAO
     */
    private EmployeeDao employeeDao;

    @Override
    public ActionForward doLogic(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        EmployeeForm employeeForm = (EmployeeForm) form;

        if (CodeConstant.OPERATE_TYPE_COMMON_EDIT.equals(employeeForm.getOperateType())) {
            // 验证输入信息
            if (!checkInput(request, employeeForm)) {
                return mapping.findForward("error");
            }

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("NAME", StringUtil.trim(employeeForm.getName()));
            param.put("GENDER", employeeForm.getGender());
            param.put("DATE", DateUtil.getCurrentDateStr(DateUtil.DATE_FORMATE_YYYYMMDD_WITH_HYPHEN));

            String message = null;
            // 提交信息
            if (StringUtil.isEmpty(employeeForm.getId())) {
                // Menu【增加职员】时
                employeeDao.insertEmployeeInfo(param);
                message = "添加成功！";
            } else {
                // 修改职员信息
                param.put("ID", employeeForm.getId());
                employeeDao.updateEmployeeInfo(param);
                message = "修改成功！";
            }
            // 清空Form
            employeeForm.clear();
            MessageUtil.setPopMessageAndRedirect(request, message, "employeeView.do");

        } else {
            if (StringUtil.isEmpty(employeeForm.getId())) {
                // 添加职员时，清空Form
                employeeForm.clear();
            } else {
                // 取得待编辑职员信息
                Map<String, Object> employeeInfo = employeeDao.getEmployeeInfo(employeeForm.getId());
                employeeForm.setGender(StringUtil.valueOf(employeeInfo.get("GENDER")));
                employeeForm.setName(StringUtil.valueOf(employeeInfo.get("NAME")));
            }
        }

        employeeForm.setOperateType(null);
        return mapping.findForward("success");
    }

    @Override
    public Class<? extends BaseAction> getClazz() {
        return EmployeeEditAction.class;
    }

    /**
     * 取得职员信息DAO
     * 
     * @return 职员信息DAO
     */
    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    /**
     * 设定职员信息DAO
     * 
     * @param employeeDao 职员信息DAO
     */
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    /**
     * 验证输入信息
     * 
     * @param request
     * @param employeeForm
     * @return 输入信息是否正确(true:正确/false:有误)
     */
    private boolean checkInput(HttpServletRequest request, EmployeeForm employeeForm) {
        if (StringUtil.isEmpty(employeeForm.getName())) {
            MessageUtil.setPopMessage(request, "姓名不能为空！");
            return false;
        }

        if (StringUtil.isEmpty(employeeForm.getGender())) {
            MessageUtil.setPopMessage(request, "性别不能为空！");
            return false;
        }

        return true;
    }
}
