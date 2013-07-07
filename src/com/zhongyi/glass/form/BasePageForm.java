package com.zhongyi.glass.form;

import org.apache.struts.action.ActionForm;

abstract class BasePageForm extends ActionForm {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6338153033649984622L;

    public static final String SEARCH_TEXT = "searchText";
    public static final String PAGE_LIMIT = "pageLimit";
    public static final String PAGE_START = "pageStart";
    int nowPage = 1;

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public void clear() {
        this.nowPage = 1;
    }
}
