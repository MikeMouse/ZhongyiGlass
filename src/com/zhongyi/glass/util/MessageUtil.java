package com.zhongyi.glass.util;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.taglib.TagUtils;

/**
 * 错误消息工具
 * 
 * @author liqianxi
 * 
 */
public class MessageUtil {
    private static final String MSG_KEY = "_msg_";
    private static final String CONFIRM_KEY = "_need_confirm_";
    private static final String REDIRECT_KEY = "_redirect_";
    private static final String INPUT_FIELD_MSG_KEY = "_input_field_msgs_";

    /**
     * 弹出消息
     * 
     * @param request
     * @param msg
     */
    public static void setPopMessage(HttpServletRequest request, String msg) {
        if (!StringUtil.isBlank(msg)) {
            clearConfirm(request);
            request.setAttribute(MSG_KEY, msg);
        }
    }

    /**
     * 弹出消息并跳转
     * 
     * @param request
     * @param msg
     * @param redirect
     */
    public static void setPopMessageAndRedirect(HttpServletRequest request,
            String msg, String redirect) {
        setPopMessage(request, msg);
        if (!StringUtil.isBlank(redirect)) {
            request.setAttribute(REDIRECT_KEY, redirect);
        }
    }

    /**
     * 弹出确认消息并跳转
     * 
     * @param request
     * @param confirm
     * @param redirect
     */
    public static void setConfirmAndRedirect(HttpServletRequest request,
            String confirm, String redirect) {
        setPopMessageAndRedirect(request, confirm, redirect);
        request.setAttribute(CONFIRM_KEY, "1");
    }

    /**
     * 清空已设置的弹出消息
     * @param request
     */
    public static void clearPopMessage(HttpServletRequest request){
        request.removeAttribute(MSG_KEY);
    }

    /**
     * 清空已设置的确认消息
     * @param request
     */
    public static void clearConfirm(HttpServletRequest request){
        clearPopMessage(request);
        request.removeAttribute(CONFIRM_KEY);
    }

    /**
     * 清空已设置的跳转信息
     * 
     * @param request
     */
    public static void clearRedirect(HttpServletRequest request) {
        request.removeAttribute(REDIRECT_KEY);
    }

    /**
     * 取得已设置的弹出消息
     * 
     * @param request
     * @return
     */
    public static String getPopMessage(HttpServletRequest request) {
        return formatMessage(StringUtil.valueOf(request.getAttribute(MSG_KEY)));
    }

    /**
     * 取得已设置的确认消息
     * 
     * @param request
     * @return
     */
    public static String getConfirm(HttpServletRequest request) {
        return formatMessage(StringUtil.valueOf(request.getAttribute(CONFIRM_KEY)));
    }

    /**
     * 取得已设置的跳转信息
     * 
     * @param request
     * @return
     */
    public static String getRedirect(HttpServletRequest request) {
        return StringUtil.valueOf(request.getAttribute(REDIRECT_KEY));
    }

    /**
     * 设置表单域消息
     * 
     * @param request
     * @param name
     * @param msg
     */
    public static void setInputFieldMassage(HttpServletRequest request,
            String name, String msg) {
        @SuppressWarnings("unchecked")
        Map<String, Object> msgs = (Map<String, Object>) request
        .getAttribute(INPUT_FIELD_MSG_KEY);
        if (msgs == null) {
            msgs = new LinkedHashMap<String, Object>(10);
            request.setAttribute(INPUT_FIELD_MSG_KEY, msgs);
        }
        if (!StringUtil.isBlank(msg) && !StringUtil.isBlank(name)) {
            msgs.put(name, msg);
        }
    }

    /**
     * 设置表单域消息
     * 
     * @param request
     * @param name
     * @param msg
     */
    public static void setInputFieldMassage(HttpServletRequest request,
            String name, ActionMessage msg) {
        @SuppressWarnings("unchecked")
        Map<String, Object> msgs = (Map<String, Object>) request
        .getAttribute(INPUT_FIELD_MSG_KEY);
        if (msgs == null) {
            msgs = new LinkedHashMap<String, Object>(10);
            request.setAttribute(INPUT_FIELD_MSG_KEY, msgs);
        }
        if (msg != null && !StringUtil.isBlank(msg.getKey())
                && !StringUtil.isBlank(name)) {
            msgs.put(name, msg);
        }
    }

    /**
     * 获取所有表单域消息
     * 
     * @param pageContext
     * @param bundle
     * @param locale
     * @return
     * @throws JspException
     */
    public static String getInputFieldMassages(PageContext pageContext,
            String bundle, String locale) throws JspException {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        boolean needPrefix = false;
        @SuppressWarnings("unchecked")
        Map<String, Object> msgs = (Map<String, Object>) pageContext
        .getRequest().getAttribute(INPUT_FIELD_MSG_KEY);
        if (msgs != null) {
            for (Map.Entry<String, Object> item : msgs.entrySet()) {
                Object msg = item.getValue();
                if (!(msg instanceof ActionMessage) && !(msg instanceof String)) {
                    continue;
                }
                if (needPrefix) {
                    sb.append('\n');
                    sb.append(',');
                } else {
                    needPrefix = true;
                }
                sb.append('\'');
                sb.append(formatMessage(item.getKey()));
                sb.append('\'');
                sb.append(':');
                sb.append('\'');
                if (msg instanceof ActionMessage) {
                    ActionMessage amsg = (ActionMessage) (item.getValue());
                    sb.append(formatMessage(TagUtils.getInstance().message(
                            pageContext, bundle, locale, amsg.getKey(),
                            amsg.getValues())));
                } else {
                    sb.append(formatMessage((String) msg));
                }
                sb.append('\'');
            }
        }
        sb.append('}');
        return sb.toString();
    }

    /**
     * 清除单个表单域消息
     * 
     * @param request
     * @param name
     * @return
     */
    public static void clearInputFieldMassage(HttpServletRequest request,
            String name) {
        @SuppressWarnings("unchecked")
        Map<String, String> msgs = (Map<String, String>) request
        .getAttribute(INPUT_FIELD_MSG_KEY);
        if (msgs != null && !StringUtil.isBlank(name)) {
            msgs.remove(name);
        }
    }

    /**
     * 清除所有表单域消息
     * 
     * @param request
     * @return
     */
    public static void clearInputFieldMassages(HttpServletRequest request) {
        request.removeAttribute(INPUT_FIELD_MSG_KEY);
    }

    /**
     * 格式化消息
     * 
     * @param msg
     * @return
     */
    private static String formatMessage(String msg) {
        if (msg != null) {
            return msg.replace("'", "\\'").replace("/", "\\/")
                    .replaceAll("[\\r\\n]+", "\\\\n");
        }
        return null;
    }
}
