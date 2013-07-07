package com.zhongyi.glass.action;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zhongyi.glass.util.PropertyUtil;

/**
 * 刷新配置
 * 
 * @author liqianxi
 * @date 2013-06-29
 */
public class RefreshConfigAction extends BaseAction {

    @Override
    public ActionForward doLogic(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        PropertyUtil.refreshConfig();
        Writer out = response.getWriter();
        out.append("OK.");
        out.close();
        return null;
    }

    @Override
    public Class<? extends BaseAction> getClazz() {
        return RefreshConfigAction.class;
    }
}
