<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*,java.lang.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib uri="/tags/zhongyi" prefix="zhongyi"%>
<html>
    <head>
        <jsp:include page="/jsp/commonHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>职员信息添加</title>
    </head>
    <body>
    <jsp:include page="/jsp/menu.jsp" />
    <div class="main">
        <html:form action="employeeEdit.do" method="post">
	        <table border="0">
	            <tr>
	                <td colspan="3"><h2>添加职员：</h2></td>
	            </tr>
	            <tr>
	                <td>姓名：</td>
	                <td>
	                    <html:text property="name" onblur="isNotEmpty('姓名不能为空', 'name');" />
	                </td>
	                <td>&nbsp;<span id='name_msg'></span>
	                </td>
	            </tr>
	            <tr>
	                <td>性别：</td>
	                <td>
	                    <html:radio property="gender" value="1">男</html:radio>
	                    <html:radio property="gender" value="0">女</html:radio>
	                </td>
                    <td>&nbsp;<span id='gender_msg'></span>
                    </td>
	            </tr>
	            <tr></tr>
	            <tr>
	                <td>
	                    <input type="button" class="button" name="edit" id="edit" value="确定" onclick="employeeConfirmSubmit();" />
	                </td>
	            </tr>
	        </table>

	        <html:hidden property="id" />
            <html:hidden property="operateType" />
        </html:form>
        </div>
    </body>
</html>
