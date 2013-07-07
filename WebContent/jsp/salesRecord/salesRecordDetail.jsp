<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*,java.lang.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib uri="/tags/zhongyi" prefix="zhongyi"%>
<html>
    <head>
        <jsp:include page="/jsp/commonHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>销售记录添加/修改</title>
    </head>
    <body>
    <jsp:include page="/jsp/menu.jsp" />
    <div class="main">
        <html:form action="salesRecordView.do" method="post">
            <div>
                <table border="0">
                    <tr>
                        <td colspan="4"><h2>顾客消费记录：</h2></td>
                    </tr>
                    <tr>
                        <td>顾客编号：</td>
                        <td><c:out value="${ SalesRecordForm.customerId }"/></td>
                        <td>姓名：</td>
                        <td><c:out value="${ SalesRecordForm.customerName }"/></td>
                    </tr>
                </table>
            </div>
            <hr style="border:none;border-top:4px solid green;" />
            <!-- 消费记录 -->
            <c:if test="${not empty SalesRecordForm.salesRecordHistoryList}">
                <c:forEach items="${SalesRecordForm.salesRecordHistoryList}" var="item">
                    <div>
                        <table>
                            <tr>
                                <td>配镜日期：</td>
                                <td>
                                    <c:out value="${ item.UPDATE_TIME }" />
                                </td>
                                <td>配镜类型：</td>
                                <td>
                                    <c:out value="${ item.GLASS_TYPE }" />
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div>
                        <div>
                            <table border="0">
                                <tr>
                                   <td>
                                       <table>
                                           <thead>
                                               <tr bgcolor="grey">
                                                   <th width="40px;"></th>
                                                   <th width="80px;">裸眼视力</th>
                                                   <th width="50px;">球镜</th>
                                                   <th width="50px;">柱镜</th>
                                                   <th width="40px;">轴位</th>
                                                   <th width="80px;">矫正视力</th>
                                                   <th id="interpupillary_distance_far_head" width="150px;">远用瞳距（毫米）</th>
                                                   <th id="interpupillary_distance_close_head" width="150px;">近用瞳距（毫米）</th>
                                               </tr>
                                           </thead>
                                           <tbody style="text-align : center;">
                                               <tr>
                                                   <td>右眼</td>
                                                   <td>
                                                       <c:out value="${ item.UNCORRECTED_VISUAL_ACUITY_RIGHT }" />
                                                   </td>
                                                   <td>
                                                       <c:out value="${ item.CONCAVE_SPHERE_RIGHT }" />
                                                   </td>
                                                   <td>
                                                       <c:out value="${ item.CONCAVE_CYLINDER_RIGHT }" />
                                                   </td>
                                                   <td>
                                                       <c:out value="${ item.AXIAL_DIRECTION_RIGHT }" />
                                                    </td>
                                                   <td>
                                                       <c:out value="${ item.CORRECTED_VISUAL_ACUITY_RIGHT }" />
                                                   </td>
                                                   <td>
                                                       <c:out value="${ item.INTERPUPILLARY_DISTANCE_FAR_RIGHT }" />
                                                   </td>
                                                   <td>
                                                       <c:out value="${ item.INTERPUPILLARY_DISTANCE_CLOSE_RIGHT }" />
                                                   </td>
                                               </tr>
                                               <tr>
                                                   <td>左眼</td>
                                                   <td>
                                                       <c:out value="${ item.UNCORRECTED_VISUAL_ACUITY_LEFT }" />
                                                   </td>
                                                   <td>
                                                       <c:out value="${ item.CONCAVE_SPHERE_LEFT }" />
                                                   </td>
                                                   <td>
                                                       <c:out value="${ item.CONCAVE_CYLINDER_LEFT }" />
                                                   </td>
                                                   <td>
                                                       <c:out value="${ item.AXIAL_DIRECTION_LEFT }" />
                                                    </td>
                                                   <td>
                                                       <c:out value="${ item.CORRECTED_VISUAL_ACUITY_LEFT }" />
                                                   </td>
                                                   <td>
                                                       <c:out value="${ item.INTERPUPILLARY_DISTANCE_FAR_LEFT }" />
                                                   </td>
                                                   <td>
                                                       <c:out value="${ item.INTERPUPILLARY_DISTANCE_CLOSE_LEFT }" />
                                                   </td>
                                               </tr>
                                           </tbody>
                                       </table>
                                   </td>
                                </tr>
                            </table>
                        </div>
                        <!-- 眼镜加工、复查及操作人员信息 -->
                        <div>
                        <table>
                            <tr>
                                <td>状态：</td>
                                <td style="width : 100px;">
                                    <c:out value="${ item.GLASS_STATUS }" />
                                </td>
                                <td>取镜日期：</td>
                                <td style="width : 100px;">
                                    <c:out value="${ item.GLASS_TAKEN_DATE }" />
                                </td>
                                <td>复查时间：</td>
                                <td style="width : 100px;">
                                    <c:out value="${ item.REEXAMINE_DATE }" />
                                </td>
                            </tr>
                            <tr>
                                <td>验光师：</td>
                                <td>
                                    <c:out value="${ item.OPTOMERTRIST }" />
                                </td>
                                <td>配镜师：</td>
                                <td>
                                    <c:out value="${ item.LENS_DISPENSER }" />
                                </td>
                                <td colspan="2">
                            </tr>
                            <tr>
                                <td>加工师：</td>
                                <td>
                                    <c:out value="${ item.PROCESSOR }" />
                                </td>
                                <td>检验员：</td>
                                <td>
                                    <c:out value="${ item.INSPECTOR }" />
                                </td>
                                <td colspan="2">
                                </td>
                            </tr>
                        </table>
                    </div>
                    </div>
                    <hr />
                    <!-- 商品信息 -->
                    <div>
                        <table>
                            <thead>
                                <tr bgcolor="grey">
                                    <th width="40px;">序号</th>
                                    <th width="80px;">商品类型</th>
                                    <th>品牌</th>
                                    <th>型号</th>
                                    <th width="100px;">单价（元）</th>
                                    <th width="40px;">数量</th>
                                    <th width="80px;">折扣类型</th>
                                    <th width="120px;">折扣</th>
                                    <th width="120px;">金额（元）</th>
                                </tr>
                            </thead>
                            <tbody style="text-align : center;">
                                <c:if test="${not empty item.SALES_GOODS_LIST}">
                                    <c:forEach items="${item.SALES_GOODS_LIST}" var="innerItem">
                                        <tr>
                                            <td>
                                                <c:out value="${innerItem.SALES_GOODS_NO}" escapeXml="true" />
                                            </td>
                                            <td>
                                                <c:out value="${innerItem.GOODS_TYPE}" escapeXml="true" />
                                            </td>
                                            <td>
                                                <c:out value="${innerItem.BRAND}" escapeXml="true" />
                                            </td>
                                            <td>
                                                <c:out value="${innerItem.BRAND_SUB_TYPE}" escapeXml="true" />
                                            </td>
                                            <td>
                                                <c:out value="${innerItem.PRICE}" escapeXml="true" />
                                            </td>
                                            <td>
                                                <c:out value="${innerItem.QUANTITY}" escapeXml="true" />
                                            </td>
                                            <td>
                                                <c:out value="${innerItem.DISCOUNT_TYPE}" escapeXml="true" />
                                            </td>
                                            <td>
                                                <c:out value="${innerItem.DISCOUNT}" escapeXml="true" />
                                            </td>
                                            <td>
                                                <c:out value="${innerItem.AMOUNT}" escapeXml="true" />
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            <tfoot>
                                <tr>
                                    <td colspan="7"></td>
                                    <td>总金额（元）：</td>
                                    <td style="text-align : center;">
                                        <c:out value="${ item.TOTAL_AMOUNT }" />
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="7"></td>
                                    <td>总额去零头：</td>
                                    <td style="text-align : center;">
                                        <c:out value="${ item.TOTAL_DISCOUNT }" />
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="7"></td>
                                    <td><span style="color : red;font-weight: bold;">消费（元）：</span></td>
                                    <td style="text-align : center;">
                                        <span id="expense_display" style="color : red;font-weight: bold;">
                                            <c:out value="${ item.EXPENSE }" />
                                        </span>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                    <hr />
                    <!-- 备注 -->
                    <div>
                        <table>
                            <tr>
                                <td>收银员：</td>
                                <td>
                                    <c:out value="${ item.CASHIER }" />
                                </td>
                            </tr>
                            <tr height="10px"></tr>
                            <tr>
                                <td>备注：</td>
                                <td>
                                    <c:out value="${ item.COMMENT }" />
                                </td>
                            </tr>
                        </table>
                    </div>
                    <hr style="border:none;border-top:4px solid green;" />
                </c:forEach>
            </c:if>

            <br />
            <div>
                <input type="button" class="button" name="back" value="返回" onclick="salesRecordDetailReturn();" />
            </div>
            <div style="height : 10px;">
            </div>

            <!-- 操作类型  -->
            <html:hidden property="operateType" />
            <!-- 顾客ID -->
            <html:hidden property="customerId" />
            <!-- 顾客姓名 -->
            <html:hidden property="customerName" />
            <!-- 移动电话 -->
            <html:hidden property="mobilePhone" />
            <!-- 家庭电话 -->
            <html:hidden property="homePhone" />
        </html:form>
    </div>
    </body>
</html>
