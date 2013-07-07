<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*,java.lang.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib uri="/tags/zhongyi" prefix="zhongyi"%>
<html>
	<head>
		<jsp:include page="/jsp/commonHead.jsp" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>职员信息查询</title>
	</head>
	<body>
    <jsp:include page="/jsp/menu.jsp" />
    <div class="main">
		<html:form action="employeeView.do" method="post">
	        <table border="0">
	            <tr>
	                <td colspan="3"><h3>职员信息查询</h3></td>
	            </tr>
	            <tr>
	                <td>
	                    <label>姓名：</label><html:text property="name" /><label>
	                    <input type="button" name="searchBtn" id="searchBtn"
	                        onclick="setOperateType('operateType', '0')" value="查询" /> </label>
	                </td>
	            </tr>
	        </table>
	        <br />
	        <table width="70%" class="t1">
	            <tr bgcolor="grey">
	                <td>
	                    <div align="center" style="color: white">职员ID</div>
	                </td>
	                <td>
	                    <div align="center" style="color: white">职员姓名</div>
	                </td>
	                <td>
	                    <div align="center" style="color: white">职员性别</div>
	                </td>
                    <td>
                        <div align="center" style="color: white">入职时间</div>
                    </td>
	                <td>
	                    <div align="center" style="color: white">操作</div>
	                </td>
	            </tr>
	            <c:if test="${not empty EmployeeForm.detailList}">
	                <c:forEach items="${EmployeeForm.detailList}" var="item">
	                    <tr>
	                        <td>
	                            <c:out value="${item.ID}" escapeXml="true" />
	                        </td>
	                        <td>
	                            <c:out value="${item.NAME}" escapeXml="true" />
	                        </td>
	                        <td>
	                            <c:out value="${item.GENDER}" escapeXml="true" />
	                        </td>
	                        <td>
	                            <c:out value="${item.CREATE_DATE}" escapeXml="true" />
	                        </td>
	                        <td>
	                            <a href="employeeEdit.do?id=${item.ID}&operateType=">修改</a>
	                        </td>
	                    </tr>
	                </c:forEach>
	            </c:if>
	        </table>
            <br />
            <zhongyi:page name="pageInfo" styleClass="pagea" />

			<!-- 操作类型  -->
			<html:hidden property="operateType" />
		</html:form>
		</div>
	</body>
</html>
