// JavaScript Document

window.onload=function(){
	var oDateTab=document.getElementById('dateDetails');
	if(oDateTab){
		var oDateItem=oDateTab.getElementsByTagName('tr');
		for(var i=0;i<oDateItem.length;i++){
			if(i%2==1){				
				oDateItem[i].className='even';	
			}	
		}	
	}	
};
Calendar = {};
Calendar.gMonths=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
Calendar.WeekDay=new Array("日","一","二","三","四","五","六");
Calendar.strToday="今天";
Calendar.strYear="年";
Calendar.strMonth="月";
Calendar.strDay="日";
Calendar.splitChar="-";
Calendar.startYear=2000;
Calendar.endYear=2050;
Calendar.dayTdHeight=12;
Calendar.dayTdTextSize=12;
Calendar.gcNotCurMonth="#E0E0E0";
Calendar.gcRestDay="#FF0000";
Calendar.gcWorkDay="#444444";
Calendar.gcMouseOver="#79D0FF";
Calendar.gcMouseOut="#fff";
Calendar.gcToday="#444444";
Calendar.gcTodayMouseOver="#6699FF";
Calendar.gcTodayMouseOut="#79D0FF";
Calendar.gdCtrl=new Object();
Calendar.goSelectTag=new Array();
Calendar.gdCurDate=new Date();
Calendar.giYear=Calendar.gdCurDate.getFullYear();
Calendar.giMonth=Calendar.gdCurDate.getMonth()+1;
Calendar.giDay=Calendar.gdCurDate.getDate();
Calendar.$=function(){var elements=new Array();for(var i=0;i<arguments.length;i++) {var element=arguments[i];if(typeof(arguments[i])=='string'){element=document.getElementById(arguments[i]);}if(arguments.length==1){return element;}elements.Push(element);}return elements;};
Array.prototype.Push=function(){var startLength=this.length;for(var i=0;i<arguments.length;i++){this[startLength+i]=arguments[i];}return this.length;};
String.prototype.HexToDec=function(){return parseInt(this,16);};
String.prototype.cleanBlank=function(){return this.isEmpty()?"":this.replace(/\s/g,"");};
Calendar.checkColor=function(){var color_tmp=(arguments[0]+"").replace(/\s/g,"").toUpperCase();var model_tmp1=arguments[1].toUpperCase();var model_tmp2="rgb("+arguments[1].substring(1,3).HexToDec()+","+arguments[1].substring(1,3).HexToDec()+","+arguments[1].substring(5).HexToDec()+")";model_tmp2=model_tmp2.toUpperCase();if(color_tmp==model_tmp1 ||color_tmp==model_tmp2){return true;}return false;};
Calendar.$V=function(){return Calendar.$(arguments[0]).value;};
Calendar.fPopCalendar=function(evt,popCtrl,dateCtrl){evt.cancelBubble=true;Calendar.gdCtrl=dateCtrl;Calendar.fSetYearMon(Calendar.giYear,Calendar.giMonth);var point=Calendar.fGetXY(popCtrl);with(Calendar.$("calendardiv").style){left=point.x+"px";top=(point.y+popCtrl.offsetHeight+1)+"px";visibility='visible';zindex='99';position='absolute';}Calendar.$("calendardiv").focus();};
Calendar.fSetDate=function(iYear,iMonth,iDay){var iMonthNew=new String(iMonth);var iDayNew=new String(iDay);if(iMonthNew.length<2){iMonthNew="0"+iMonthNew;}if(iDayNew.length<2){iDayNew="0"+iDayNew;}Calendar.gdCtrl.value=iYear+Calendar.splitChar+iMonthNew+Calendar.splitChar+iDayNew;Calendar.fHideCalendar();};
Calendar.fHideCalendar=function(){Calendar.$("calendardiv").style.visibility="hidden";for(var i=0;i<Calendar.goSelectTag.length;i++){Calendar.goSelectTag[i].style.visibility="visible";}Calendar.goSelectTag.length=0;};
Calendar.fSetSelected=function(){var iOffset=0;var iYear=parseInt(Calendar.$("tbSelYear").value);var iMonth=parseInt(Calendar.$("tbSelMonth").value);var aCell=Calendar.$("cellText"+arguments[0]);aCell.bgColor=Calendar.gcMouseOut;with(aCell){var iDay=parseInt(innerHTML);if(Calendar.checkColor(style.color,Calendar.gcNotCurMonth)){iOffset=(innerHTML>10)?-1:1;}iMonth+=iOffset;if(iMonth<1){iYear--;iMonth=12;}else if(iMonth>12){iYear++;iMonth=1;}}Calendar.fSetDate(iYear,iMonth,iDay);if(Calendar.handler){Calendar.handler();}};
Calendar.Point=function(iX,iY){this.x=iX;this.y=iY;};
Calendar.fBuildCal=function(iYear,iMonth){var aMonth=new Array();for(var i=1;i<7;i++){aMonth[i]=new Array(i);}var dCalDate=new Date(iYear,iMonth-1,1);var iDayOfFirst=dCalDate.getDay();var iDaysInMonth=new Date(iYear,iMonth,0).getDate();var iOffsetLast=new Date(iYear,iMonth-1,0).getDate()-iDayOfFirst+1;var iDate=1;var iNext=1;for(var d=0;d<7;d++){aMonth[1][d]=(d<iDayOfFirst)?(iOffsetLast+d)*(-1):iDate++;}for(var w=2;w<7;w++){for(var d=0;d<7;d++){aMonth[w][d]=(iDate<=iDaysInMonth)?iDate++:(iNext++)*(-1);}}return aMonth;};
Calendar.fDrawCal=function(iYear,iMonth,iCellHeight,iDateTextSize){var colorTD=" bgcolor='"+Calendar.gcMouseOut+"' bordercolor='"+Calendar.gcMouseOut+"'";var styleTD=" valign='middle' align='center' style='height:"+iCellHeight+"px;font-weight:bolder;font-size:"+iDateTextSize+"px;";var dateCal="";dateCal+="<tr>";for(var i=0;i<7;i++){dateCal+="<td"+colorTD+styleTD+"color:#990099'>"+Calendar.WeekDay[i]+"</td>";}dateCal+="</tr>";for(var w=1;w<7;w++){dateCal+="<tr>";for(var d=0;d<7;d++){var tmpid=w+""+d;dateCal+="<td"+styleTD+"cursor:pointer;' onclick='Calendar.fSetSelected("+tmpid+")'>";dateCal+="<span id='cellText"+tmpid+"'></span>";dateCal+="</td>";}dateCal+="</tr>";}return dateCal;};
Calendar.fUpdateCal=function(iYear,iMonth){var myMonth=Calendar.fBuildCal(iYear,iMonth);for(var w=1;w<7;w++){for(var d=0;d<7;d++){with(Calendar.$("cellText"+w+""+d)){parentNode.bgColor=Calendar.gcMouseOut;parentNode.borderColor=Calendar.gcMouseOut;parentNode.onmouseover=function(){this.bgColor=Calendar.gcMouseOver;};parentNode.onmouseout=function(){this.bgColor=Calendar.gcMouseOut;};if(myMonth[w][d]<0){style.color=Calendar.gcNotCurMonth;innerHTML=Math.abs(myMonth[w][d]);}else{style.color=((d==0)||(d==6))?Calendar.gcRestDay:Calendar.gcWorkDay;innerHTML=myMonth[w][d];if(iYear==Calendar.giYear && iMonth==Calendar.giMonth && myMonth[w][d]==Calendar.giDay){style.color=Calendar.gcToday;parentNode.bgColor=Calendar.gcTodayMouseOut;parentNode.onmouseover=function(){this.bgColor=Calendar.gcTodayMouseOver;};parentNode.onmouseout=function(){this.bgColor=Calendar.gcTodayMouseOut;};}}}}}};
Calendar.fSetYearMon=function(iYear,iMon){Calendar.$("tbSelMonth").options[iMon-1].selected=true;for(var i=0;i<Calendar.$("tbSelYear").length;i++){if(Calendar.$("tbSelYear").options[i].value==iYear){Calendar.$("tbSelYear").options[i].selected=true;}}Calendar.fUpdateCal(iYear,iMon);};
Calendar.fPrevMonth=function(){var iMon=Calendar.$("tbSelMonth").value;var iYear=Calendar.$("tbSelYear").value;if(--iMon<1){iMon=12;iYear--;}Calendar.fSetYearMon(iYear,iMon);};
Calendar.fNextMonth=function(){var iMon=Calendar.$("tbSelMonth").value;var iYear=Calendar.$("tbSelYear").value;if(++iMon>12){iMon=1;iYear++;}Calendar.fSetYearMon(iYear,iMon);};
Calendar.fGetXY=function(aTag){var oTmp=aTag;var pt=new Calendar.Point(0,0);do{pt.x+=oTmp.offsetLeft;pt.y+=oTmp.offsetTop;oTmp=oTmp.offsetParent;}while(oTmp.tagName.toUpperCase()!="BODY");return pt;};
Calendar.getDateDiv=function(){var noSelectForIE="";var noSelectForFireFox="";if(document.all){noSelectForIE="onselectstart='return false;'";}else{noSelectForFireFox="-moz-user-select:none;";}var dateDiv="";dateDiv+="<div id='calendardiv' onclick='event.cancelBubble=true' "+noSelectForIE+" style='"+noSelectForFireFox+"position:absolute;z-index:99;visibility:hidden;border:1px solid #999999;'>";dateDiv+="<table border='0' bgcolor='#fff' cellpadding='0' cellspacing='1' >";dateDiv+="<tr>";dateDiv+="<td><input type='button' id='PrevMonth' value='<' style='height:20px;width:20px;font-weight:bolder;' onclick='Calendar.fPrevMonth()'>";dateDiv+="</td><td><select id='tbSelYear' style='border:1px solid;' onchange='Calendar.fUpdateCal(Calendar.$V(\"tbSelYear\"),Calendar.$V(\"tbSelMonth\"))'>";for(var i=Calendar.startYear;i<Calendar.endYear;i++){dateDiv+="<option value='"+i+"'>"+i+Calendar.strYear+"</option>";}dateDiv+="</select></td><td>";dateDiv+="<select id='tbSelMonth' style='border:1px solid;' onchange='Calendar.fUpdateCal(Calendar.$V(\"tbSelYear\"),Calendar.$V(\"tbSelMonth\"))'>";for(var i=0;i<12;i++){dateDiv+="<option value='"+(i+1)+"'>"+Calendar.gMonths[i]+"</option>";}dateDiv+="</select></td><td>";dateDiv+="<input type='button' id='NextMonth' value='>' style='height:20px;width:20px;font-weight:bolder;' onclick='Calendar.fNextMonth()'>";dateDiv+="</td>";dateDiv+="</tr><tr>";dateDiv+="<td align='center' colspan='4'>";dateDiv+="<div style='background-color:#cccccc'><table width='100%' border='0' cellpadding='3' cellspacing='1'>";dateDiv+=Calendar.fDrawCal(Calendar.giYear,Calendar.giMonth,Calendar.dayTdHeight,Calendar.dayTdTextSize);dateDiv+="</table></div>";dateDiv+="</td>";dateDiv+="</tr><tr><td align='center' colspan='4' nowrap>";dateDiv+="<span style='cursor:pointer;font-weight:bolder;' onclick='Calendar.fSetDate(Calendar.giYear,Calendar.giMonth,Calendar.giDay)' onmouseover='this.style.color=\""+Calendar.gcMouseOver+"\"' onmouseout='this.style.color=\"#000000\"'>"+Calendar.strToday+":"+Calendar.giYear+Calendar.strYear+Calendar.giMonth+Calendar.strMonth+Calendar.giDay+Calendar.strDay+"</span>";dateDiv+="</tr></tr>";dateDiv+="</table></div>";return dateDiv;};
with(document){onclick=Calendar.fHideCalendar;write(Calendar.getDateDiv());}
// 设置日期
Calendar.setDate = function(date){
	if(date){
		var ds = date.split('-');
		if(ds.length==3){
			Calendar.gdCurDate = new Date(parseInt(ds[0],10),parseInt(ds[1],10)-1,parseInt(ds[2],10));
			Calendar.giYear=Calendar.gdCurDate.getFullYear();
			Calendar.giMonth=Calendar.gdCurDate.getMonth()+1;
			Calendar.giDay=Calendar.gdCurDate.getDate();
		}
	}
};
// 弹出日期选择器，并绑定回调函数
var fPopCalendar = function(evt,popCtrl,dateCtrl,date,handler){
	Calendar.setDate(date);
	Calendar.fPopCalendar(evt,popCtrl,dateCtrl);
	Calendar.handler = handler;
};
/**
 * 自动矫正日期范围相关的表单域
 * 
 * @param beginField
 *            起始日期表单域名称
 * @param endField
 *            结束日期表单域名称
 * @param autoSetField
 *            自动矫正表单域名称
 */
var autoSetDateRange = function(beginField, endField, autoSetField) {
	return function autoSetOtherDate() {
		var beginDom = $('input[name=' + beginField + ']');
		var endDom = $('input[name=' + endField + ']');
		if (beginDom && beginDom.val() && endDom && endDom.val()) {
			if ((beginDom.val().replace(/-/g, '') - 0) > (endDom.val()
					.replace(/-/g, '') - 0)) {
				if (autoSetField == endField) {
					endDom.val(beginDom.val());
				} else if (autoSetField == beginField) {
					beginDom.val(endDom.val());
				}
			}
		}
	};
};
