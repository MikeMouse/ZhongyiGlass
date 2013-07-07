// 校验时间
function validateDateTime(element) {
	var elementValue = element.value;
	var r = elementValue.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
	if (r == null) {
		setErrorMsg('日期格式应为yyyy-MM-dd', element.name);
		return false;
	}
	var d = new Date(r[1], r[3] - 1, r[4]);
	if ((d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4])) {
		cleanErrorMsg(element.name);
		return true;
	} else {
		setErrorMsg('非法日期，请重新输入', element.name);
		return false;
	}
}

// 校验数字字母
function validateNumOrAlpha(element){
	var elementValue = element.value;
	var r = elementValue.match(/^([a-zA-Z0-9\u4e00-\u9fa5]){1,32}$/);
	if (r == null) {
		setErrorMsg('只能中英文和数字且长度为1-32位', element.name);
		return false;
	}
	cleanErrorMsg(element.name);
	return true;
}

//校验邮箱
function validateEmail(element){
	var elementValue = element.value;
	var r = elementValue.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/);
	if (r == null) {
		setErrorMsg('邮箱格式错误', element.name);
		return false;
	}
	cleanErrorMsg(element.name);
	return true;
}

// 校验数字
function validateNum(elementName) {
	var elementValue = document.getElementsByName(elementName)[0].value;
	if (elementValue == "") {
		setErrorMsg('请输入', elementName);
		return false;
	}
	if (isNaN(elementValue)) {
		setErrorMsg('请输入数字', elementName);
		return false;
	}
	cleanErrorMsg(elementName);
	return true;
}

// 检验数值
function isNumber(value) {
	if (!value) {
		return true;
	}

	var regu = "^[0-9]\\d*(.\\d+)*$";
	 var re = new RegExp(regu);
	 if (re.test(value))
	  return true;
	 else
	  return false;
}

//检验整数
function isInteger(value) {
	if (!value) {
		return true;
	}

	var regu = "^[0-9]\\d*$";
	 var re = new RegExp(regu);
	 if (re.test(value))
	  return true;
	 else
	  return false;
}

function setErrorMsg(msg, elementName) {
	document.getElementById(elementName + '_msg').innerHTML = '<font color="red">*&nbsp;'
			+ msg + '</font><br />';
}

function cleanErrorMsg(elementName) {
	document.getElementById(elementName + '_msg').innerHTML = '';
}

// 是否为空字符串校验
function isNotEmpty(errMsg, elementName) {
	var elementValue = document.getElementsByName(elementName)[0].value;
	if (elementValue != null && elementValue.length > 0) {
		cleanErrorMsg(elementName);
		return true;
	} else {
		setErrorMsg(errMsg, elementName);
		return false;
	}
}

function errorMsg() {
	if ('${!empty messages}' == 'true') {
		alert('${messages }');
	}
}

function resetPage() {
	if(document.getElementsByName("nowPage")[0] != undefined) {
		document.getElementsByName("nowPage")[0].value = '1';
	}
	document.forms[0].submit();
}

function goPage(page) {
	document.getElementsByName("nowPage")[0].value = page;
	if (document.getElementsByName("operateType") != undefined && document.getElementsByName("operateType").length > 0) {
		// 运营数据查询用
		setOperateType("operateType", "1");
	}
	document.forms[0].submit();
}

function formSubmit(url) {
	window.opener.location.href = url;
	window.close();
}

var showMsg = function(msg) {
	if (msg) {
		alert(msg);
	}
};

var fixUrl = function(url) {
	if (url) {
		var index = url.indexOf('?');
		var tmp = '';
		if (index >= 0) {
			tmp = url.substring(index, url.length);
			url = url.substring(0, index);
		}
		return url.replace(/([^:])[\/]{2,}/g, '$1/') + tmp;
	}
};

var popAndRedirect = function(url, msg, needConfirm) {
	if(needConfirm){
		if(msg && confirm(msg) && url){
			location.href = fixUrl(url);
		}
	}else{
		showMsg(msg);
		if (url) {
			location.href = fixUrl(url);
		}
	}
};

/**
 * 显示错误消息,参数类型JSON，格式{表单域1:消息1, ...表单域n:消息n}，显示规则如下。 如果表单域存在：
 * 检查是否存在ID为特定格式（表单域名称+'_msg'）的节点： 若存在，则将消息输出至该节点， 否则将消息输出至该表单域右侧10px的浮出层；
 * 如果表单域不存在： 则将消息输出至当前页面的左上方。
 */
var showMsgs = function(msgs) {
	var toHead = '';
	for ( var name in msgs) {
		var dom = jQuery('input[name=' + name + ']');
		if (!dom || !dom.size()) {
			toHead += '<font color="red">*&nbsp;' + msgs[name]
					+ '</font><br />';
			delete msgs[name];
		}
	}
	if (toHead) {
		jQuery('body').prepend(toHead);
	}
	for ( var name in msgs) {
		var dom = jQuery('input[name=' + name + ']');
		var showDom = jQuery('#' + name + '_msg');
		if (showDom && showDom.size()) {
			showDom
					.append('<font color="red">*&nbsp;' + msgs[name]
							+ '</font>');
		} else {
			jQuery('body').append(
					'<div style="position: absolute; z-index: 2000; top: '
							+ dom.offset().top + 'px; left: '
							+ (dom.offset().left + dom.width() + 10)
							+ 'px; display: block; "><font color="red">*&nbsp;'
							+ msgs[name] + '</font></div>');
		}
	}
};
/**
 * 图片预览
 */
function previewImage(file)
 {
	var MAXWIDTH = 100;
	var MAXHEIGHT = 100;
	var div = document.getElementById('preview');
	if (file.files && file.files[0]) {
		div.innerHTML = '<img id=imghead>';
		var img = document.getElementById('imghead');
		img.onload = function() {
			var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth,
					img.offsetHeight);
			img.width = rect.width;
			img.height = rect.height;
			img.style.marginLeft = rect.left + 'px';
			img.style.marginTop = rect.top + 'px';
		};
		var reader = new FileReader();
		reader.onload = function(evt) {
			img.src = evt.target.result;
		};
		reader.readAsDataURL(file.files[0]);
	} else {
		var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
		file.select();
		var src = document.selection.createRange().text;
		div.innerHTML = '<img id=imghead>';
		var img = document.getElementById('imghead');
		img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
		var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth,
				img.offsetHeight);
		status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
		div.innerHTML = "<div id=divhead style='width:" + rect.width
				+ "px;height:" + rect.height + "px;margin-top:" + rect.top
				+ "px;margin-left:" + rect.left + "px;" + sFilter + src
				+ "\"'></div>";
	}
}
function clacImgZoomParam( maxWidth, maxHeight, width, height ){
	var param = {top:0, left:0, width:width, height:height};
	if( width>maxWidth || height>maxHeight )
	{
		rateWidth = width / maxWidth;
		rateHeight = height / maxHeight;
		
		if( rateWidth > rateHeight )
		{
			param.width =  maxWidth;
			param.height = Math.round(height / rateWidth);
		}else
		{
			param.width = Math.round(width / rateHeight);
			param.height = maxHeight;
		}
	}
	
	param.left = Math.round((maxWidth - param.width) / 2);
	param.top = Math.round((maxHeight - param.height) / 2);
	return param;
}

/**
 * 设定操作类型
 * 
 * @param name 操作类型元素名称
 * @param value 操作类型元素值
 */
function setOperateType(name, value) {
	if (document.getElementsByName(name) != undefined) {
		document.getElementsByName(name)[0].value = value;
	}
	document.getElementsByTagName("form")[0].submit();
}

/**
 * 职员编辑页面提交确认
 */
function employeeConfirmSubmit() {
	if (confirm('确认提交？')) {
		setOperateType('operateType', '2');
	}
}

/**
 * 职员编辑页面提交确认
 */
function customerConfirmSubmit() {
	if (confirm('确认提交？')) {
		setOperateType('operateType', '2');
	}
}

/**
 * 销售记录编辑页面提交确认
 */
function salesRecordConfirmSubmit() {
	if (confirm('确认提交？')) {
		if (!document.getElementsByName("salesRecordId")[0].value) {
			// 验证顾客编号
			if (!document.getElementsByName("customerId")[0].value) {
				alert("顾客编号不能为空！");
				return false;
			}
			// 验证配镜信息
			if (document.getElementsByName("glassType")[0].value) {
				// 状态
				if (document.getElementsByName("glassStatus")[0].value) {
					if (document.getElementsByName("glassStatus")[0].value == '2' && !document.getElementsByName("glassTakenDate")[0].value) {
						alert("状态为\"已取镜\"时，取镜日期不能为空！");
						return false;
					}
				} else {
					alert("状态不能为空！");
					return false;
				}
				// 验光师
				if (!document.getElementsByName("optometrist")[0].value) {
					alert("验光师不能为空！");
					return false;
				}
				// 配镜师
				if (!document.getElementsByName("lensDispenser")[0].value) {
					alert("配镜师不能为空！");
					return false;
				}
				// 加工师
				if (!document.getElementsByName("processor")[0].value) {
					alert("加工师不能为空！");
					return false;
				}
				// 检验员
				if (!document.getElementsByName("inspector")[0].value) {
					alert("检验员不能为空！");
					return false;
				}
			}
			// 验证消费金额
			if (!document.getElementsByName("expense")[0].value) {
				alert("商品列表至少包含一件商品！");
				return false;
			}
			// 验证收银员
			if (!document.getElementsByName("cashier")[0].value) {
				alert("收银员不能为空！");
				return false;
			}
		}

		setOperateType('operateType', '2');
	}
}

function salesRecordDetailReturn() {
	document.getElementsByName("customerId")[0].value = '';
	document.getElementsByName("customerName")[0].value = '';
	document.getElementsByName("mobilePhone")[0].value = '';
	document.getElementsByName("homePhone")[0].value = '';
	setOperateType('operateType', '');
}

/**
 * 配镜类型选择值变化时，相关输入项目联动处理
 */
function glassTypeChange() {
	if (document.getElementsByName("glassType")[0].value == '1') {
		document.getElementById("interpupillary_distance_far_head").style.display = "none";
		document.getElementById("interpupillary_distance_far_body_left").style.display = "none";
		document.getElementById("interpupillary_distance_far_body_right").style.display = "none";
		document.getElementById("interpupillary_distance_close_head").style.display = "none";
		document.getElementById("interpupillary_distance_close_body_left").style.display = "none";
		document.getElementById("interpupillary_distance_close_body_right").style.display = "none";
		document.getElementById("reexamine_date_label").style.display = "table-cell";
		document.getElementById("reexamine_date").style.display = "table-cell";
		document.getElementById("eyesight_examine").style.display = "block";
	} else if (document.getElementsByName("glassType")[0].value == '0')  {
		document.getElementById("interpupillary_distance_far_head").style.display = "table-cell";
		document.getElementById("interpupillary_distance_far_body_left").style.display = "table-cell";
		document.getElementById("interpupillary_distance_far_body_right").style.display = "table-cell";
		document.getElementById("interpupillary_distance_close_head").style.display = "table-cell";
		document.getElementById("interpupillary_distance_close_body_left").style.display = "table-cell";
		document.getElementById("interpupillary_distance_close_body_right").style.display = "table-cell";
		document.getElementById("reexamine_date_label").style.display = "none";
		document.getElementById("reexamine_date").style.display = "none";
		document.getElementById("eyesight_examine").style.display = "block";
	} else {
		// 裸眼视力（左）
		document.getElementsByName("uncorrectedVisualAcuityLeft")[0].value = "";
		// 球镜（左）
		document.getElementsByName("concaveSphereLeft")[0].value = "";
		// 柱镜（左）
		document.getElementsByName("concaveCylinderLeft")[0].value = "";
		// 轴位（左）
		document.getElementsByName("axialDirectionLeft")[0].value = "";
		// 矫正视力（左）
		document.getElementsByName("correctedVisualAcuityLeft")[0].value = "";
		// 远用瞳距（左）
		document.getElementsByName("interpupillaryDistanceFarLeft")[0].value = "";
		// 近用瞳距（左）
		document.getElementsByName("interpupillaryDistanceCloseLeft")[0].value = "";
		// 裸眼视力（右）
		document.getElementsByName("uncorrectedVisualAcuityRight")[0].value = "";
		// 球镜（右）
		document.getElementsByName("concaveSphereRight")[0].value = "";
		// 柱镜（右）
		document.getElementsByName("concaveCylinderRight")[0].value = "";
		// 轴位（右）
		document.getElementsByName("axialDirectionRight")[0].value = "";
		// 矫正视力（右）
		document.getElementsByName("correctedVisualAcuityRight")[0].value = "";
		// 远用瞳距（右）
		document.getElementsByName("interpupillaryDistanceFarRight")[0].value = "";
		// 近用瞳距（右）
		document.getElementsByName("interpupillaryDistanceCloseRight")[0].value = "";
		// 状态
		document.getElementsByName("glassStatus")[0].value = "";
		// 取镜日期
		document.getElementsByName("glassTakenDate")[0].value = "";
		// 复查日期
		document.getElementsByName("reExamineDate")[0].value = "";
		// 验光师
		document.getElementsByName("optometrist")[0].value = "";
		// 配镜师
		document.getElementsByName("lensDispenser")[0].value = "";
		// 加工师
		document.getElementsByName("processor")[0].value = "";
		// 检验员
		document.getElementsByName("inspector")[0].value = "";
		document.getElementById("eyesight_examine").style.display = "none";
	}
}

/**
 * 商品类型选择值变化时，相关输入项目联动处理
 * @param goodsNo 商品序号
 */
function goodsTypeChange(goodsNo) {
	var i = parseInt(goodsNo) - 1;
	if(document.getElementsByName("goodsType")[i].value) {
		document.getElementsByName("goodsBrand")[i].readOnly = false;
		document.getElementsByName("goodsBrandSubType")[i].readOnly = false;
		document.getElementsByName("goodsPrice")[i].readOnly = false;
		document.getElementsByName("goodsQuantity")[i].readOnly = false;
	} else {
		document.getElementsByName("goodsBrand")[i].value = '';
		document.getElementsByName("goodsBrand")[i].readOnly = true;
		document.getElementsByName("goodsBrandSubType")[i].value = '';
		document.getElementsByName("goodsBrandSubType")[i].readOnly = true;
		document.getElementsByName("goodsPrice")[i].value = '';
		document.getElementsByName("goodsPrice")[i].readOnly = true;
		document.getElementsByName("goodsQuantity")[i].value = '';
		document.getElementsByName("goodsQuantity")[i].readOnly = true;
		// 修改折扣类型
		document.getElementsByName("goodsDiscountType")[i].value = '';
		// 折扣
		document.getElementsByName("goodsDiscount")[i].value = '';
		document.getElementsByName("goodsDiscount")[i].readOnly = true;
		// 金额
		document.getElementsByName("goodsAmount")[i].value = '';
		// 重新计算消费金额
		caculateGoodsTotalAmount();
	}
}

/**
 * 折扣类型选择值变化时，相关输入项目联动处理
 * @param goodsNo 商品序号
 */
function goodsDiscountTypeChange(goodsNo) {
	var i = parseInt(goodsNo) - 1;
	if(document.getElementsByName("goodsDiscountType")[i].value) {
		document.getElementsByName("goodsDiscount")[i].value = '';
		document.getElementsByName("goodsDiscount")[i].readOnly = false;
	} else {
		document.getElementsByName("goodsDiscount")[i].value = '';
		document.getElementsByName("goodsDiscount")[i].readOnly = true;
	}

	caculateAmount(goodsNo, 4);
}

/**
 * 添加一行商品
 */
function addGoods() {
	var goodsBody = document.getElementById("goods_body");
	// 当前商品行数
	var totalRows = goodsBody.getElementsByTagName("tr").length;
	// 当前最后一行商品
	var lastRow = goodsBody.getElementsByTagName("tr")[totalRows - 1];
	var newTr = lastRow.cloneNode(true);
	var newRowTd = newTr.getElementsByTagName("td");
	// 修改序号
	newRowTd[0].firstChild.nodeValue = parseInt(newRowTd[0].firstChild.nodeValue) + 1;
	// 修改商品类型onclick
	var goodsTypeSelect = newRowTd[1].getElementsByTagName("select");
	goodsTypeSelect[0].setAttribute("onchange", "goodsTypeChange(" + newRowTd[0].firstChild.nodeValue + ")");
	// 修改品牌readonly
	var brandInput = newRowTd[2].getElementsByTagName("input");
	brandInput[0].setAttribute("readOnly", true);
	// 修改型号readonly
	var brandSubTypeInput = newRowTd[3].getElementsByTagName("input");
	brandSubTypeInput[0].setAttribute("readOnly", true);
	// 修改单价readonly、增加onblur事件
	var priceInput = newRowTd[4].getElementsByTagName("input");
	priceInput[0].setAttribute("readOnly", true);
	priceInput[0].setAttribute("onblur", "caculateAmount(" + newRowTd[0].firstChild.nodeValue + ", 1)");
	// 修改数量readonly、增加onblur事件
	var quantityInput = newRowTd[5].getElementsByTagName("input");
	quantityInput[0].setAttribute("readOnly", true);
	quantityInput[0].setAttribute("onblur", "caculateAmount(" + newRowTd[0].firstChild.nodeValue + ", 2)");
	// 修改折扣类型onclick
	var discountTypeSelect = newRowTd[6].getElementsByTagName("select");
	discountTypeSelect[0].setAttribute("onchange", "goodsDiscountTypeChange(" + newRowTd[0].firstChild.nodeValue + ")");
	// 修改折扣readonly、增加onblur事件
	var discountInput = newRowTd[7].getElementsByTagName("input");
	discountInput[0].setAttribute("readOnly", true);
	discountInput[0].setAttribute("onblur", "caculateAmount(" + newRowTd[0].firstChild.nodeValue + ", 3)");
	// 增加行
	goodsBody.appendChild(newTr);
}

/**
 * 计算购买商品的总金额
 */
function caculateGoodsTotalAmount() {
	var goodsAmountArray = document.getElementsByName("goodsAmount");
	var totalAmount = 0;
	for (var i = 0; i < goodsAmountArray.length; i++) {
		if (goodsAmountArray[i].value != "") {
			totalAmount += parseInt(goodsAmountArray[i].value);
		}
	}

	document.getElementsByName("totalAmount")[0].value = totalAmount;
	document.getElementById("totalAmount_display").innerText = totalAmount;

	// 计算顾客消费金额
	caculateExpense();
}

/**
 * 计算顾客消费金额
 */
function caculateExpense() {
	var totalDiscount = 0;
	// 验证总金额去零头的输入值
	if (document.getElementsByName("totalDiscount")[0].value == "") {
		document.getElementsByName("totalDiscount")[0].value = 0;
	}

	if (isInteger(document.getElementsByName("totalDiscount")[0].value)) {
		totalDiscount = document.getElementsByName("totalDiscount")[0].value;
	} else {
		alert("【总额去零头】的值必须为整数!");
		return false;
	}

	if (document.getElementsByName("totalAmount")[0].value != "") {
		if (parseInt(document.getElementsByName("totalAmount")[0].value) < totalDiscount) {
			document.getElementsByName("totalDiscount")[0].value = 0;
			document.getElementById("expense_display").innerText = 0;
		} else {
			var expense = document.getElementsByName("totalAmount")[0].value - totalDiscount;
			document.getElementsByName("expense")[0].value = expense;
			document.getElementById("expense_display").innerText = expense;
		}
	}
}

/**
 * 计算金额
 * @param goodsNo 商品序号
 * @param item 修改项目(1：单价/2：数量/3：折扣/4：折扣类型)
 */
function caculateAmount(goodsNo, item) {
	var i = parseInt(goodsNo) - 1;
	var isCaculate = false;
	// 验证失去焦点的页面项目的输入值是否正确
	if (item == 1 && !document.getElementsByName("goodsPrice")[i].readOnly) {
		// 单价
		if (document.getElementsByName("goodsPrice")[i].value == "") {
			return false;
		} else if (!isNumber(document.getElementsByName("goodsPrice")[i].value)) {
			alert("商品列表第" + goodsNo + "行的【单价】列的值必须为数值！" );
			return false;
		}
		isCaculate = true;
	} else if (item == 2 && !document.getElementsByName("goodsQuantity")[i].readOnly) {
		// 数量
		if (document.getElementsByName("goodsQuantity")[i].value == "") {
			return false;
		} else if (!isInteger(document.getElementsByName("goodsQuantity")[i].value) || document.getElementsByName("goodsQuantity")[i].value == 0) {
			alert("商品列表第" + goodsNo + "行的【数量】的值必须为大于0的整数！" );
			return false;
		}
		isCaculate = true;
	} else if (item == 3 && !document.getElementsByName("goodsDiscount")[i].readOnly) {
		// 折扣
		if (document.getElementsByName("goodsDiscount")[i].value == "") {
			// 计算商品金额
		} else if (!isNumber(document.getElementsByName("goodsDiscount")[i].value) || document.getElementsByName("goodsDiscount")[i].value == 0) {
			alert("商品列表第" + goodsNo + "行的【折扣】列的值必须为大于0的数！" );
			return false;
		} else {
			if (document.getElementsByName("goodsPrice")[i].value) {
				if (document.getElementsByName("goodsDiscountType")[i].value == '0') {
					// 去零头
					if (document.getElementsByName("goodsPrice")[i].value <= parseInt(document.getElementsByName("goodsDiscount")[i].value)) {
						alert("折扣类型为\"去零头\"时，商品列表第" + goodsNo + "行的【折扣】列的值必须小于该商品的单价！" );
						return false;
					}
				} else if (document.getElementsByName("goodsDiscountType")[i].value == '1') {
					if (document.getElementsByName("goodsDiscount")[i].value <= 0 || document.getElementsByName("goodsDiscount")[i].value > 99) {
						alert("折扣类型为\"打折\"时，商品列表第" + goodsNo + "行的【折扣】列的值必须在1~99之间！" );
						return false;
					}
				}
			}
		}

		isCaculate = true;
	} else if (item == 4) {
		isCaculate = true;
	}

	// 输入值正确时，计算商品金额及商品总金额
	if (isCaculate) {
		// 单价
		var price = document.getElementsByName("goodsPrice")[i].value;
		// 数量
		var quantity = document.getElementsByName("goodsQuantity")[i].value;
		// 优惠
		var discount = 0;

		if (document.getElementsByName("goodsDiscount")[i].value != "") {
			// 折扣类型
			var goodsDiscountType = document.getElementsByName("goodsDiscountType")[i].value;
			if (goodsDiscountType == '0') {
				// 去零头
				discount = document.getElementsByName("goodsDiscount")[i].value;
			} else if (goodsDiscountType == '1') {
				// 折扣
				discount = price - parseInt(price * document.getElementsByName("goodsDiscount")[i].value / 100);
			}
		}

		// 单价及数量均输入正确值时，计算商品金额及商品总金额
		if (price && quantity) {
			// 计算商品金额
			document.getElementsByName("goodsAmount")[i].value = (price - discount) * quantity;
			// 计算购买商品总金额
			caculateGoodsTotalAmount();
		}
	}
}
