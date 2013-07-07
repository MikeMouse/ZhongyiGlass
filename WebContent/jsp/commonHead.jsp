<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=basePath %>css/style.css" type="text/css" rel="stylesheet" /> 
<script src="<%=basePath %>js/jquery-1.4.2.min.js"></script>
<script src="<%=basePath %>js/util.js"></script>
<script type="text/javascript">
var basePath = '<%=basePath %>';
jQuery(function(){
	var redirect = '<%=com.zhongyi.glass.util.MessageUtil.getRedirect(request)%>';
	popAndRedirect(redirect ? basePath + redirect : redirect,'<%=com.zhongyi.glass.util.MessageUtil.getPopMessage(request)%>','<%=com.zhongyi.glass.util.MessageUtil.getConfirm(request)%>');
	showMsgs(<%=com.zhongyi.glass.util.MessageUtil.getInputFieldMassages(pageContext, null, null)%>);
});
</script>
<script src="<%=basePath %>js/calendar.js"></script>
