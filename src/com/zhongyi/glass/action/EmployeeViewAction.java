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
import com.zhongyi.glass.dao.EmployeeDao;
import com.zhongyi.glass.form.EmployeeForm;
import com.zhongyi.glass.page.Page;
import com.zhongyi.glass.util.StringUtil;

/**
 * 职员信息查看
 * 
 * @author liqianxi
 * @date 2013-06-29
 */
public class EmployeeViewAction extends BaseAction {

    /**
     * 职员信息DAO
     */
    private EmployeeDao employeeDao;

    @Override
    public ActionForward doLogic(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        EmployeeForm employeeForm = (EmployeeForm) form;

        Map<String, Object> param = new HashMap<String, Object>();
        int count = 0;
        // 详细列表
        List<Map<String, Object>> detailList = null;
        if (CodeConstant.OPERATE_TYPE_COMMON_SEARCH.equals(employeeForm.getOperateType())) {
            param.put("NAME", StringUtil.trim(employeeForm.getName()));
        }

        count = employeeDao.getEmployeeCount(param);
        int nowPage = employeeForm.getNowPage();
        Page pageInfo = new Page(nowPage, count);
        if (count > 0) {
            detailList = employeeDao.getEmployeeList(param, pageInfo.getRecordStart() - 1, pageInfo.getPageSize());
        }

        // 编辑详细列表记录信息
        editDetailList(detailList);
        employeeForm.setDetailList(detailList);
        employeeForm.setOperateType(null);
        request.setAttribute("pageInfo", pageInfo);
        return mapping.findForward("success");
    }

    @Override
    public Class<? extends BaseAction> getClazz() {
        return EmployeeViewAction.class;
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
                } else {
                    item.put("GENDER", CodeConstant.GENDER_DISPLAY_MALE);
                }

                item.put("CREATE_DATE", StringUtil.valueOf(item.get("CREATE_DATE")));
            }
        }
    }
}
