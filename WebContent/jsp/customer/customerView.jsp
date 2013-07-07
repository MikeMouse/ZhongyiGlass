<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*,java.lang.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib uri="/tags/zhongyi" prefix="zhongyi"%>
<html>
	<head>
		<jsp:include page="/jsp/commonHead.jsp" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>顾客信息查询</title>
	</head>
	<body>
    <jsp:include page="/jsp/menu.jsp" />
    <div class="main">
		<html:form action="customerView.do" method="post">
	        <table border="0">
	            <tr>
	                <td colspan="3"><h3>顾客信息查询</h3></td>
	            </tr>
	            <tr>
	                <td>
	                    <label>姓名：</label><html:text property="name" />
                        <label>手机：</label><html:text property="mobilePhone" />
                        <label>家庭电话：</label><html:text property="homePhone" />
	                    <input type="button" name="searchBtn" id="searchBtn"
	                        onclick="setOperateType('operateType', '0')" value="查询" />
	                </td>
	            </tr>
	        </table>
	        <br />
	        <table width="70%" class="t1">
	            <tr bgcolor="grey">
	                <td>
	                    <div align="center" style="color: white">顾客ID</div>
	                </td>
	                <td>
	                    <div align="center" style="color: white">顾客姓名</div>
	                </td>
	                <td>
	                    <div align="center" style="color: white">顾客性别</div>
	                </td>
                    <td>
                        <div align="center" style="color: white">手机</div>
                    </td>
	                <td>
	                    <div align="center" style="color: white">家庭电话</div>
	                </td>
                    <td>
                        <div align="center" style="color: white">操作</div>
                    </td>
	            </tr>
	            <c:if test="${not empty CustomerForm.detailList}">
	                <c:forEach items="${CustomerForm.detailList}" var="item">
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
	                            <c:out value="${item.MOBILE_PHONE}" escapeXml="true" />
	                        </td>
                            <td>
                                <c:out value="${item.HOME_PHONE}" escapeXml="true" />
                            </td>
	                        <td>
	                            <a href="customerEdit.do?id=${item.ID}&operateType=">修改</a>
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
