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
        <script type="text/javascript">
		    $(document).ready(function () {
		    	if($("[name='salesRecordId']").val() != "") {
		    		if ($("[name=reExamineDate]").val() == "") {
		    			// 架镜
	                    $("#reexamine_date_label").css("display", "none");
	                    $("#reexamine_date").css("display", "none");
		    		} else {
                        // 隐形
                        $("#interpupillary_distance_far_head").css("display", "none");
                        $("#interpupillary_distance_close_head").css("display", "none");
                        $("#interpupillary_distance_far_body_left").css("display", "none");
                        $("#interpupillary_distance_far_body_right").css("display", "none");
                        $("#interpupillary_distance_close_body_left").css("display", "none");
                        $("#interpupillary_distance_close_body_right").css("display", "none");
		    		}
		    	}
		    });
		</script>
    </head>
    <body>
    <jsp:include page="/jsp/menu.jsp" />
    <div class="main">
        <html:form action="salesRecordEdit.do" method="post">
            <div>
                <table border="0">
                    <c:choose>
                        <c:when test="${empty SalesRecordForm.salesRecordId}">
		                    <tr>
                                <td colspan="2"><h2>添加销售记录：</h2></td>
                            </tr>
		                    <tr>
		                        <td>顾客编号：</td>
		                        <td><html:text property="customerId" /></td>
		                    </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="4"><h2>编辑销售记录：</h2></td>
                            </tr>
                            <tr>
                                <td>顾客编号：</td>
                                <td><c:out value="${ SalesRecordForm.customerId }"/></td>
                                <td>姓名：</td>
                                <td><c:out value="${ SalesRecordForm.customerName }"/></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </table>
            </div>
            <hr />
            <!-- 两眼视力情况 -->
            <c:choose>
                <c:when test="${empty SalesRecordForm.salesRecordId}">
		            <div>
		                <table>
		                    <tr>
		                        <td>配镜类型：</td>
		                        <td>
		                            <html:select property="glassType" onchange="glassTypeChange();">
		                                <html:option value=""></html:option>
		                                <html:option value="0">架镜</html:option>
		                                <html:option value="1">隐形</html:option>
		                            </html:select>
		                        </td>
		                        <td colspan="2"></td>
		                    </tr>
		                </table>
		            </div>
		            <div id="eyesight_examine" style="display : none;">
		                <div>
		                    <table border="0">
		                        <tr>
		                           <td>
		                               <table>
		                                   <thead>
		                                       <tr bgcolor="grey">
		                                           <th width="40px;"></th>
		                                           <th width="80px;">裸眼视力</th>
		                                           <th width="40px;">球镜</th>
		                                           <th width="40px;">柱镜</th>
		                                           <th width="40px;">轴位</th>
		                                           <th width="80px;">矫正视力</th>
		                                           <th id="interpupillary_distance_far_head" width="160px;">远用瞳距（毫米）</th>
		                                           <th id="interpupillary_distance_close_head" width="160px;">近用瞳距（毫米）</th>
		                                       </tr>
		                                   </thead>
		                                   <tbody>
                                               <tr>
                                                   <td>右眼</td>
                                                   <td>
                                                       <html:select property="uncorrectedVisualAcuityRight">
                                                           <c:if test="${not empty SalesRecordForm.visualAcuityList}">
                                                               <html:option value=""></html:option>
                                                               <html:optionsCollection property="visualAcuityList" value="id" label="value" />
                                                           </c:if>
                                                       </html:select>
                                                   </td>
                                                   <td>
                                                       <html:select property="concaveSphereRight">
                                                           <c:if test="${not empty SalesRecordForm.concaveSphereList}">
                                                               <html:option value=""></html:option>
                                                               <html:optionsCollection property="concaveSphereList" value="id" label="value" />
                                                           </c:if>
                                                       </html:select>
                                                   </td>
                                                   <td>
                                                       <html:select property="concaveCylinderRight">
                                                           <c:if test="${not empty SalesRecordForm.concaveCylinderList}">
                                                               <html:option value=""></html:option>
                                                               <html:optionsCollection property="concaveCylinderList" value="id" label="value" />
                                                           </c:if>
                                                       </html:select>
                                                   </td>
                                                   <td><html:text property="axialDirectionRight" /></td>
                                                   <td>
                                                       <html:select property="correctedVisualAcuityRight">
                                                           <c:if test="${not empty SalesRecordForm.visualAcuityList}">
                                                               <html:option value=""></html:option>
                                                               <html:optionsCollection property="visualAcuityList" value="id" label="value" />
                                                           </c:if>
                                                       </html:select>
                                                   </td>
                                                   <td id="interpupillary_distance_far_body_right" ><html:text property="interpupillaryDistanceFarRight" /></td>
                                                   <td id="interpupillary_distance_close_body_right" ><html:text property="interpupillaryDistanceCloseRight" /></td>
                                               </tr>
		                                       <tr>
		                                           <td>左眼</td>
		                                           <td>
		                                               <html:select property="uncorrectedVisualAcuityLeft">
		                                                   <c:if test="${not empty SalesRecordForm.visualAcuityList}">
		                                                       <html:option value=""></html:option>
		                                                       <html:optionsCollection property="visualAcuityList" value="id" label="value" />
		                                                   </c:if>
		                                               </html:select>
		                                           </td>
		                                           <td>
		                                               <html:select property="concaveSphereLeft">
		                                                   <c:if test="${not empty SalesRecordForm.concaveSphereList}">
		                                                       <html:option value=""></html:option>
		                                                       <html:optionsCollection property="concaveSphereList" value="id" label="value" />
		                                                   </c:if>
		                                               </html:select>
		                                           </td>
		                                           <td>
		                                               <html:select property="concaveCylinderLeft">
		                                                   <c:if test="${not empty SalesRecordForm.concaveCylinderList}">
		                                                       <html:option value=""></html:option>
		                                                       <html:optionsCollection property="concaveCylinderList" value="id" label="value" />
		                                                   </c:if>
		                                               </html:select>
		                                           </td>
		                                           <td><html:text property="axialDirectionLeft" /></td>
		                                           <td>
		                                               <html:select property="correctedVisualAcuityLeft">
		                                                   <c:if test="${not empty SalesRecordForm.visualAcuityList}">
		                                                       <html:option value=""></html:option>
		                                                       <html:optionsCollection property="visualAcuityList" value="id" label="value" />
		                                                   </c:if>
		                                               </html:select>
		                                           </td>
		                                           <td id="interpupillary_distance_far_body_left"><html:text property="interpupillaryDistanceFarLeft" /></td>
		                                           <td id="interpupillary_distance_close_body_left"><html:text property="interpupillaryDistanceCloseLeft" /></td>
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
		                        <td>
		                            <html:select property="glassStatus">
		                                <html:option value=""></html:option>
		                                <html:option value="0">等待加工</html:option>
		                                <html:option value="1">等待取镜</html:option>
		                                <html:option value="2">已取镜</html:option>
		                            </html:select>
		                        </td>
		                        <td>取镜日期：</td>
		                        <td>
		                            <html:text style="width : 80px;" property="glassTakenDate" styleClass="dateInput" readonly="true" onclick="fPopCalendar(event,this,this,this.value,null)" onfocus="this.select()" />
		                        </td>
		                        <td id="reexamine_date_label">复查时间：</td>
		                        <td id="reexamine_date">
		                            <html:text style="width : 80px;" property="reExamineDate" styleClass="dateInput" readonly="true" onclick="fPopCalendar(event,this,this,this.value,null)" onfocus="this.select()" />
		                        </td>
		                    </tr>
		                    <tr>
		                        <td>验光师：</td>
		                        <td>
		                            <html:select property="optometrist">
		                                <c:if test="${not empty SalesRecordForm.employeeList}">
		                                    <html:option value=""></html:option>
		                                    <html:optionsCollection property="employeeList" value="id" label="name" />
		                                </c:if>
		                            </html:select>
		                        </td>
		                        <td>配镜师：</td>
		                        <td>
		                            <html:select property="lensDispenser">
		                                <c:if test="${not empty SalesRecordForm.employeeList}">
		                                    <html:option value=""></html:option>
		                                    <html:optionsCollection property="employeeList" value="id" label="name" />
		                                </c:if>
		                            </html:select>
		                        </td>
		                        <td colspan="2">
		                    </tr>
		                    <tr>
		                        <td>加工师：</td>
		                        <td>
		                            <html:select property="processor">
		                                <c:if test="${not empty SalesRecordForm.employeeList}">
		                                    <html:option value=""></html:option>
		                                    <html:optionsCollection property="employeeList" value="id" label="name" />
		                                </c:if>
		                            </html:select>
		                        </td>
		                        <td>检验员：</td>
		                        <td>
		                            <html:select property="inspector">
		                                <c:if test="${not empty SalesRecordForm.employeeList}">
		                                    <html:option value=""></html:option>
		                                    <html:optionsCollection property="employeeList" value="id" label="name" />
		                                </c:if>
		                            </html:select>
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
		                <input type="button" class="button" name="add" value="添加商品" onclick="addGoods();" />
		                <br />
		                <table>
		                    <thead>
		                        <tr bgcolor="grey">
		                            <th>序号</th>
		                            <th>商品类型</th>
		                            <th>品牌</th>
		                            <th>型号</th>
		                            <th>单价（元）</th>
		                            <th>数量</th>
		                            <th>折扣类型</th>
		                            <th>折扣</th>
		                            <th>金额（元）</th>
		                        </tr>
		                    </thead>
		                    <tbody id="goods_body">
		                        <tr>
		                            <td>1</td>
		                            <td>
		                                <html:select property="goodsType" onchange="goodsTypeChange(1);">
		                                    <c:if test="${not empty SalesRecordForm.goodsTypeList}">
		                                        <html:option value=""></html:option>
		                                        <html:optionsCollection property="goodsTypeList" value="id" label="name" />
		                                    </c:if>
		                                </html:select>
		                            </td>
		                            <td><html:text style="width : 180px;" property="goodsBrand" readonly="true" /></td>
		                            <td><html:text style="width : 180px;" property="goodsBrandSubType" readonly="true" /></td>
		                            <td><html:text style="width : 100px;" property="goodsPrice" readonly="true" onblur="caculateAmount(1, 1)" /></td>
		                            <td><html:text style="width : 60px;" property="goodsQuantity" readonly="true" onblur="caculateAmount(1, 2)"/></td>
		                            <td>
		                                <html:select property="goodsDiscountType" onchange="goodsDiscountTypeChange(1);">
		                                    <html:option value=""></html:option>
		                                    <html:option value="0">去零头</html:option>
		                                    <html:option value="1">打折</html:option>
		                                </html:select>
		                            </td>
		                            <td><html:text style="width : 100px;" property="goodsDiscount" readonly="true" onblur="caculateAmount(1, 3)"/></td>
		                            <td><html:text style="width : 110px;" property="goodsAmount" readonly="true" /></td>
		                        </tr>
		                        <tr>
		                            <td>2</td>
		                            <td>
		                                <html:select property="goodsType" onchange="goodsTypeChange(2);">
		                                    <c:if test="${not empty SalesRecordForm.goodsTypeList}">
		                                        <html:option value=""></html:option>
		                                        <html:optionsCollection property="goodsTypeList" value="id" label="name" />
		                                    </c:if>
		                                </html:select>
		                            </td>
		                            <td><html:text style="width : 180px;" property="goodsBrand" readonly="true" /></td>
		                            <td><html:text style="width : 180px;" property="goodsBrandSubType" readonly="true" /></td>
		                            <td><html:text style="width : 100px;" property="goodsPrice" readonly="true" onblur="caculateAmount(2, 1)" /></td>
		                            <td><html:text style="width : 60px;" property="goodsQuantity" readonly="true" onblur="caculateAmount(2, 2)" /></td>
		                            <td>
		                                <html:select property="goodsDiscountType" onchange="goodsDiscountTypeChange(2);">
		                                    <html:option value=""></html:option>
		                                    <html:option value="0">去零头</html:option>
		                                    <html:option value="1">打折</html:option>
		                                </html:select>
		                            </td>
		                            <td><html:text style="width : 100px;" property="goodsDiscount" readonly="true" onblur="caculateAmount(2, 3)" /></td>
		                            <td><html:text style="width : 110px;" property="goodsAmount" readonly="true" /></td>
		                        </tr>
		                        <tr>
		                            <td>3</td>
		                            <td>
		                                <html:select property="goodsType" onchange="goodsTypeChange(3);">
		                                    <c:if test="${not empty SalesRecordForm.goodsTypeList}">
		                                        <html:option value=""></html:option>
		                                        <html:optionsCollection property="goodsTypeList" value="id" label="name" />
		                                    </c:if>
		                                </html:select>
		                            </td>
		                            <td><html:text style="width : 180px;" property="goodsBrand" readonly="true" /></td>
		                            <td><html:text style="width : 180px;" property="goodsBrandSubType" readonly="true" /></td>
		                            <td><html:text style="width : 100px;" property="goodsPrice" readonly="true" onblur="caculateAmount(3, 1)" /></td>
		                            <td><html:text style="width : 60px;" property="goodsQuantity" readonly="true" onblur="caculateAmount(3, 2)" /></td>
		                            <td>
		                                <html:select property="goodsDiscountType" onchange="goodsDiscountTypeChange(3);">
		                                    <html:option value=""></html:option>
		                                    <html:option value="0">去零头</html:option>
		                                    <html:option value="1">打折</html:option>
		                                </html:select>
		                            </td>
		                            <td><html:text style="width : 100px;" property="goodsDiscount" readonly="true" onblur="caculateAmount(3, 3)" /></td>
		                            <td><html:text style="width : 110px;" property="goodsAmount" readonly="true" /></td>
		                        </tr>
		                        <tr>
		                            <td>4</td>
		                            <td>
		                                <html:select property="goodsType" onchange="goodsTypeChange(4);">
		                                    <c:if test="${not empty SalesRecordForm.goodsTypeList}">
		                                        <html:option value=""></html:option>
		                                        <html:optionsCollection property="goodsTypeList" value="id" label="name" />
		                                    </c:if>
		                                </html:select>
		                            </td>
		                            <td><html:text style="width : 180px;" property="goodsBrand" readonly="true" /></td>
		                            <td><html:text style="width : 180px;" property="goodsBrandSubType" readonly="true" /></td>
		                            <td><html:text style="width : 100px;" property="goodsPrice" readonly="true" onblur="caculateAmount(4, 1)" /></td>
		                            <td><html:text style="width : 60px;" property="goodsQuantity" readonly="true" onblur="caculateAmount(4, 2)" /></td>
		                            <td>
		                                <html:select property="goodsDiscountType" onchange="goodsDiscountTypeChange(4);">
		                                    <html:option value=""></html:option>
		                                    <html:option value="0">去零头</html:option>
		                                    <html:option value="1">打折</html:option>
		                                </html:select>
		                            </td>
		                            <td><html:text style="width : 100px;" property="goodsDiscount" readonly="true" onblur="caculateAmount(4, 3)" /></td>
		                            <td><html:text style="width : 110px;" property="goodsAmount" readonly="true" /></td>
		                        </tr>
		                        <tr>
		                            <td>5</td>
		                            <td>
		                                <html:select property="goodsType" onchange="goodsTypeChange(5);">
		                                    <c:if test="${not empty SalesRecordForm.goodsTypeList}">
		                                        <html:option value=""></html:option>
		                                        <html:optionsCollection property="goodsTypeList" value="id" label="name" />
		                                    </c:if>
		                                </html:select>
		                            </td>
		                            <td><html:text style="width : 180px;" property="goodsBrand" readonly="true" /></td>
		                            <td><html:text style="width : 180px;" property="goodsBrandSubType" readonly="true" /></td>
		                            <td><html:text style="width : 100px;" property="goodsPrice" readonly="true" onblur="caculateAmount(5, 1)" /></td>
		                            <td><html:text style="width : 60px;" property="goodsQuantity" readonly="true" onblur="caculateAmount(5, 2)" /></td>
		                            <td>
		                                <html:select property="goodsDiscountType" onchange="goodsDiscountTypeChange(5);">
		                                    <html:option value=""></html:option>
		                                    <html:option value="0">去零头</html:option>
		                                    <html:option value="1">打折</html:option>
		                                </html:select>
		                            </td>
		                            <td><html:text style="width : 100px;" property="goodsDiscount" readonly="true" onblur="caculateAmount(5, 3)" /></td>
		                            <td><html:text style="width : 110px;" property="goodsAmount" readonly="true" /></td>
		                        </tr>
		                    </tbody>
		                    <tfoot>
		                        <tr>
		                            <td colspan="7"></td>
		                            <td>总金额（元）：</td>
		                            <td><span id="totalAmount_display"></span></td>
		                        </tr>
		                        <tr>
		                            <td colspan="7"></td>
		                            <td>总额去零头：</td>
		                            <td><html:text style="width : 110px;" property="totalDiscount" value="0" onblur="caculateExpense();" /></td>
		                        </tr>
		                        <tr>
		                            <td colspan="7"></td>
		                            <td><span style="color : red;font-weight: bold;">消费（元）：</span></td>
		                            <td><span id="expense_display" style="color : red;font-weight: bold;"></span></td>
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
		                            <html:select property="cashier">
		                                <c:if test="${not empty SalesRecordForm.employeeList}">
		                                    <html:option value=""></html:option>
		                                    <html:optionsCollection property="employeeList" value="id" label="name" />
		                                </c:if>
		                            </html:select>
		                        </td>
		                    </tr>
		                    <tr height="10px"></tr>
		                    <tr>
		                        <td>备注：</td>
		                        <td><html:textarea style="width : 500px;height : 100px;" property="comment" /></td>
		                    </tr>
		                </table>
		            </div>
                </c:when>
                <c:otherwise>
                    <div>
                        <table>
                            <tr>
                                <td>配镜类型：</td>
                                <td>
                                    <c:out value="${ SalesRecordForm.glassType }" />
                                </td>
                                <td colspan="2"></td>
                            </tr>
                        </table>
                    </div>
                    <div id="eyesight_examine">
                        <div>
                            <table border="0">
                                <tr>
                                   <td>
                                       <table>
                                           <thead>
                                               <tr bgcolor="grey">
                                                   <th width="40px;"></th>
                                                   <th width="80px;">裸眼视力</th>
                                                   <th width="40px;">球镜</th>
                                                   <th width="40px;">柱镜</th>
                                                   <th width="40px;">轴位</th>
                                                   <th width="80px;">矫正视力</th>
                                                   <th id="interpupillary_distance_far_head" width="160px;">远用瞳距（毫米）</th>
                                                   <th id="interpupillary_distance_close_head" width="160px;">近用瞳距（毫米）</th>
                                               </tr>
                                           </thead>
                                           <tbody>
                                               <tr>
                                                   <td>右眼</td>
                                                   <td>
                                                       <c:out value="${ SalesRecordForm.uncorrectedVisualAcuityRight }" />
                                                   </td>
                                                   <td>
                                                       <c:out value="${ SalesRecordForm.concaveSphereRight }" />
                                                   </td>
                                                   <td>
                                                       <c:out value="${ SalesRecordForm.concaveCylinderRight }" />
                                                   </td>
                                                   <td>
                                                       <c:out value="${ SalesRecordForm.axialDirectionRight }" />
                                                   </td>
                                                   <td>
                                                       <c:out value="${ SalesRecordForm.correctedVisualAcuityRight }" />
                                                   </td>
                                                   <td id="interpupillary_distance_far_body_right">
                                                       <c:out value="${ SalesRecordForm.interpupillaryDistanceFarRight }" />
                                                   </td>
                                                   <td id="interpupillary_distance_close_body_right">
                                                       <c:out value="${ SalesRecordForm.interpupillaryDistanceCloseRight }" />
                                                   </td>
                                               </tr>
                                               <tr>
                                                   <td>左眼</td>
                                                   <td>
                                                       <c:out value="${ SalesRecordForm.uncorrectedVisualAcuityLeft }" />
                                                   </td>
                                                   <td>
                                                       <c:out value="${ SalesRecordForm.concaveSphereLeft }" />
                                                   </td>
                                                   <td>
                                                       <c:out value="${ SalesRecordForm.concaveCylinderLeft }" />
                                                   </td>
                                                   <td>
                                                       <c:out value="${ SalesRecordForm.axialDirectionLeft }" />
                                                    </td>
                                                   <td>
                                                       <c:out value="${ SalesRecordForm.correctedVisualAcuityLeft }" />
                                                   </td>
                                                   <td id="interpupillary_distance_far_body_left">
                                                       <c:out value="${ SalesRecordForm.interpupillaryDistanceFarLeft }" />
                                                   </td>
                                                   <td id="interpupillary_distance_close_body_left">
                                                       <c:out value="${ SalesRecordForm.interpupillaryDistanceCloseLeft }" />
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
                                <td>
                                    <html:select property="glassStatus">
                                        <html:option value=""></html:option>
                                        <html:option value="0">等待加工</html:option>
                                        <html:option value="1">等待取镜</html:option>
                                        <html:option value="2">已取镜</html:option>
                                    </html:select>
                                </td>
                                <td>取镜日期：</td>
                                <td>
                                    <html:text style="width : 80px;" property="glassTakenDate" styleClass="dateInput" readonly="true" onclick="fPopCalendar(event,this,this,this.value,null)" onfocus="this.select()" />
                                </td>
                                <td id="reexamine_date_label">复查时间：</td>
                                <td id="reexamine_date">
                                    <html:text style="width : 80px;" property="reExamineDate" styleClass="dateInput" readonly="true" onclick="fPopCalendar(event,this,this,this.value,null)" onfocus="this.select()" />
                                </td>
                            </tr>
                            <tr>
                                <td>验光师：</td>
                                <td>
                                    <c:out value="${ SalesRecordForm.optometrist }" />
                                </td>
                                <td>配镜师：</td>
                                <td>
                                    <c:out value="${ SalesRecordForm.lensDispenser }" />
                                </td>
                                <td colspan="2">
                            </tr>
                            <tr>
                                <td>加工师：</td>
                                <td>
                                    <c:out value="${ SalesRecordForm.processor }" />
                                </td>
                                <td>检验员：</td>
                                <td>
                                    <c:out value="${ SalesRecordForm.inspector }" />
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
                                    <th>序号</th>
                                    <th>商品类型</th>
                                    <th>品牌</th>
                                    <th>型号</th>
                                    <th>单价（元）</th>
                                    <th>数量</th>
                                    <th>折扣类型</th>
                                    <th>折扣</th>
                                    <th>金额（元）</th>
                                </tr>
                            </thead>
                            <tbody id="goods_body">
                                <c:if test="${not empty SalesRecordForm.goodsList}">
	                                <c:forEach items="${SalesRecordForm.goodsList}" var="item">
				                        <tr>
				                            <td>
				                                <c:out value="${item.SALES_GOODS_NO}" escapeXml="true" />
				                            </td>
	                                        <td>
	                                            <c:out value="${item.GOODS_TYPE}" escapeXml="true" />
	                                        </td>
	                                        <td>
	                                            <c:out value="${item.BRAND}" escapeXml="true" />
	                                        </td>
	                                        <td>
	                                            <c:out value="${item.BRAND_SUB_TYPE}" escapeXml="true" />
	                                        </td>
                                            <td>
                                                <c:out value="${item.PRICE}" escapeXml="true" />
                                            </td>
	                                        <td>
	                                            <c:out value="${item.QUANTITY}" escapeXml="true" />
	                                        </td>
                                            <td>
                                                <c:out value="${item.DISCOUNT_TYPE}" escapeXml="true" />
                                            </td>
                                            <td>
                                                <c:out value="${item.DISCOUNT}" escapeXml="true" />
                                            </td>
                                            <td>
                                                <c:out value="${item.AMOUNT}" escapeXml="true" />
                                            </td>
				                        </tr>
	                                </c:forEach>
                                </c:if>
                            <tfoot>
                                <tr>
                                    <td colspan="7"></td>
                                    <td>总金额（元）：</td>
                                    <td>
                                        <c:out value="${ SalesRecordForm.totalAmount }" />
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="7"></td>
                                    <td>总额去零头：</td>
                                    <td>
                                        <c:out value="${ SalesRecordForm.totalDiscount }" />
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="7"></td>
                                    <td><span style="color : red;font-weight: bold;">消费（元）：</span></td>
                                    <td>
	                                    <span id="expense_display" style="color : red;font-weight: bold;">
                                            <c:out value="${ SalesRecordForm.expense }" />
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
                                    <c:out value="${ SalesRecordForm.cashier }" />
                                </td>
                            </tr>
                            <tr height="10px"></tr>
                            <tr>
                                <td>备注：</td>
                                <td><html:textarea style="width : 500px;height : 100px;" property="comment" /></td>
                            </tr>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>
            <br />
            <div>
                <input type="button" class="button" name="confirm" value="确定" onclick="salesRecordConfirmSubmit();" />
            </div>
            <div style="height : 10px;">
            </div>

            <!-- 操作类型  -->
            <html:hidden property="operateType" />
            <!-- 商品总金额  -->
            <html:hidden property="totalAmount" />
            <!-- 消费金额  -->
            <html:hidden property="expense" />
            <!-- 消费记录ID  -->
            <html:hidden property="salesRecordId" />
        </html:form>
    </div>
    </body>
</html>
