package com.zhongyi.glass.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.tools.ant.Project;

public abstract class BaseAction extends Action {
    protected Project p;
    Logger logger = Logger.getLogger(getClazz());

    @Override
    final public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        ActionForward result = null;
        logger.debug("Start.");
        try {
            result = doLogic(mapping, form, request, response);
        } catch (Throwable t) {
            response.setStatus(400);
            logger.error("", t);
        } finally {
            logger.debug("End.");
        }
        return result;
    }

    abstract public ActionForward doLogic(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception;

    abstract public Class<? extends BaseAction> getClazz();
}
