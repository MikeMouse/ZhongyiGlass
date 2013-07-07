<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
    <h1>中义眼镜</h1>
</div>
<hr />
<div class="navigator">
	<ul>
        <li>销售信息
            <ul>
                <li><a href="salesRecordView.do?operateType=">查看销售记录</a></li>
                <li><a href="salesRecordEdit.do?id=&operateType=">增加销售记录</a></li>
            </ul>
        </li>
		<li>顾客信息
			<ul>
				<li><a href="customerView.do?operateType=">查看顾客</a></li>
				<li><a href="customerEdit.do?salesRecordId=&operateType=">增加顾客</a></li>
			</ul>
		</li>
        <li>职员信息
            <ul>
                <li><a href="employeeView.do?operateType=">查看职员</a></li>
                <li><a href="employeeEdit.do?id=&operateType=">增加职员</a></li>
            </ul>
        </li>
	</ul>
</div>
