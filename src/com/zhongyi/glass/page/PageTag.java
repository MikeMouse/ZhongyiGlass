package com.zhongyi.glass.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;


public class PageTag extends BodyTagSupport {

    private static final long serialVersionUID = -9066400742828834250L;

    private String name;

    private String styleClass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            HttpServletRequest request = (HttpServletRequest) super.pageContext
                    .getRequest();
            Object obj = request.getAttribute(name);
            if (obj == null) {
                return 0;
            }
            if (!(obj instanceof Page)) {
                return 0;
            }
            Page page = (Page) obj;
            StringBuffer sb = new StringBuffer();

            // 可根据page的get方法设定内容,我的如下:
            sb.append("<input type='button' value='首页'"
                    + getReadOnly(page.isHasPrePage())
                    + "onclick=\"javascript:goPage(1)\"/>&nbsp;");
            sb.append("<input type='button' value='上一页'"
                    + getReadOnly(page.isHasPrePage())
                    + "onclick=\"javascript:goPage(" + (page.getPageNo() - 1)
                    + ")\"/>&nbsp;");
            if (page.getShowStart() > 1) {
                sb.append("...&nbsp;");
            }
            // 当前页面前的内容
            for (int i = page.getShowStart(); i < page.getPageNo(); i++) {
                sb.append("<a href='javascript:goPage(" + i + ")'>" + i
                        + "</a>&nbsp;");
            }
            // 当前页面内容
            sb.append("<a href='javascript:goPage(" + page.getPageNo()
                    + ")' class='" + styleClass + "'>" + page.getPageNo()
                    + "</a>&nbsp;");
            // 当前页面后的内容
            for (int i = page.getPageNo() + 1; i < page.getShowEnd(); i++) {
                sb.append("<a href='javascript:goPage(" + i + ")'>" + i
                        + "</a>&nbsp;");
            }
            if (page.getShowEnd() < page.getAllPage()) {
                sb.append("...&nbsp;");
            }
            sb.append("<input type='button' value='下一页'"
                    + getReadOnly(page.isHasNextPage())
                    + "onclick=\"javascript:goPage(" + (page.getPageNo() + 1)
                    + ")\"/>&nbsp;");
            sb.append("<input type='button' value='尾页'"
                    + getReadOnly(page.isHasNextPage())
                    + "onclick=\"javascript:goPage(" + page.getAllPage()
                    + ")\"/>");
            sb.append("<input type=\"hidden\" name=\"nowPage\" id=\"nowPage\"/>");
            JspWriter jspwriter = super.pageContext.getOut();
            jspwriter.write(sb.toString());
        } catch (Exception exception) {
            throw new JspException(exception);
        }
        return 0;
    }

    private String getReadOnly(boolean b) {
        if (!b) {
            return " disabled class='disableButton'";
        }
        return " class='button'";
    }
}
