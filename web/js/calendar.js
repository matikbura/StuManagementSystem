

var webCalendar = new TWebCalendar();
document.write("<div id='meizzCalendarLayer' name='meizzCalendarLayer' style='position: absolute; z-index: 9999; width: 144px; height: 193px; display: none'>");
document.write('<iframe id="meizzCalendarIframe" scrolling="no" frameborder="0" width="100%" height="100%"></iframe></div>');

var Browser = new Object();
Browser.isMozilla = (typeof document.implementation != 'undefined') && (typeof document.implementation.createDocument != 'undefined') && (typeof HTMLDocument != 'undefined');
Browser.isIE = window.ActiveXObject ? true : false;
Browser.isFirefox = (navigator.userAgent.toLowerCase().indexOf("firefox") != - 1);
Browser.isSafari = (navigator.userAgent.toLowerCase().indexOf("safari") != - 1);
Browser.isOpera = (navigator.userAgent.toLowerCase().indexOf("opera") != - 1);

//主调函数
function calendar(e){
    webCalendar.iframe2=document.getElementById("meizzCalendarIframe").contentWindow;
    webCalendar.calendar=document.getElementById("meizzCalendarLayer");
        
    e=Browser.isIE?window.event.srcElement:e.target;    
    writeIframe();
    var o = webCalendar.calendar.style; 
    webCalendar.eventSrc = e;
	if (arguments.length == 0 || arguments.length == 1){
	    webCalendar.objExport = e;
    }else{
		if(typeof(arguments[1])=='object'){
			webCalendar.objExport=arguments[1];
		}else{
        	webCalendar.objExport = document.getElementById(arguments[1]);
		}
    }

	var t = e.offsetTop,  h = e.clientHeight, l = e.offsetLeft, p = e.type;
	while (e = e.offsetParent){t += e.offsetTop; l += e.offsetLeft;}
    o.display = ""; webCalendar.iframe2.document.body.focus();
    
    var cw = webCalendar.calendar.clientWidth, ch = webCalendar.calendar.clientHeight;
    var dw = document.body.clientWidth, dl = document.body.scrollLeft, dt = document.body.scrollTop;
    
    if (document.body.clientHeight + dt - t - h >= ch){
        o.top = ((p=="image")? t + h : t + h + 6)+'px'; 
    }else {
        o.top  = ((t - dt < ch) ? ((p=="image")? t + h : t + h + 6) : t - ch)+'px';
    }
    
    if (dw + dl - l >= cw){
         o.left = l+'px'; 
    }else{ 
        o.left = ((dw >= cw) ? dw - cw + dl : dl)+'px';
    }
    
    if  (!webCalendar.timeShow) webCalendar.dateReg = /^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2})$/;
    else webCalendar.dateReg = /^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;

    try{
        if (webCalendar.objExport.value.trim() != ""){
            webCalendar.dateStyle = webCalendar.objExport.value.trim().match(webCalendar.dateReg);
            if (webCalendar.dateStyle == null){
                webCalendar.thisYear   = new Date().getFullYear();
                webCalendar.thisMonth  = new Date().getMonth()+ 1;
                webCalendar.thisDay    = new Date().getDate();
                alert("原文本框里的日期有错误！\n可能与你定义的显示时分秒有冲突！");
                writeCalendar();
                return false;
            }else{
                webCalendar.thisYear   = parseInt(webCalendar.dateStyle[1], 10);
                webCalendar.thisMonth  = parseInt(webCalendar.dateStyle[3], 10);
                webCalendar.thisDay    = parseInt(webCalendar.dateStyle[4], 10);
                webCalendar.inputDate  = parseInt(webCalendar.thisDay, 10) +"/"+ parseInt(webCalendar.thisMonth, 10) +"/"+ 
                parseInt(webCalendar.thisYear, 10); 
                writeCalendar();
            }
        }else{
        	writeCalendar();
        }
    }catch(e){
    	writeCalendar();
    } 
}

function writeIframe()
{
    var strIframe ="<html><head><meta http-equiv='Content-Type' content='text/html; charset=gb2312'><style>"+
    "*{font-size: 12px; font-family: 宋体}"+
    ".bg{color: "+ webCalendar.lightColor +
    "; cursor: default; background-color: "+ webCalendar.darkColor +";}"+
    "table#tableMain{ width: 142; height: 180;}"+
    "table#tableWeek td{ color: "+ webCalendar.lightColor +";}"+
    "table#tableDay  td{ font-weight: bold;}"+
    "td#meizzYearHead, td#meizzYearMonth{color: "+ webCalendar.wordColor +"}"+
    ".out { text-align: center; border-top: 1px solid "+ webCalendar.DarkBorder +
    "; border-left: 1px solid "+ webCalendar.DarkBorder +";"+
    "border-right: 1px solid "+ webCalendar.lightColor +
    "; border-bottom: 1px solid "+ webCalendar.lightColor +";}"+
    ".over{ text-align: center; border-top: 1px solid #FFFFFF; border-left: 1px solid #FFFFFF;"+
    "border-bottom: 1px solid "+ webCalendar.DarkBorder +
    "; border-right: 1px solid "+ webCalendar.DarkBorder +"}"+
    "input{ border: 1px solid "+ webCalendar.darkColor +
    "; padding-top: 1px; height: 18; cursor: hand;"+
    " color:"+ webCalendar.wordColor +
    "; background-color: "+ webCalendar.btnBgColor +"}"+
    "</style></head><body onselectstart='return false' style='margin: 0px' oncontextmenu='return false'><form name='meizz'>";

    if (webCalendar.drag){ 
        strIframe += "<scr"+"ipt language=javascript>\n"+
    		"var drag=false, cx=0, cy=0, o = parent.webCalendar.calendar;\n"+
    		"document.onkeyup=function(){\n"+
    		"   switch(window.event.keyCode){\n"+
    		"       case 27 : parent.hiddenCalendar(); break;\n"+
    		"       case 37 : parent.prevM(); break; \n"+
    		"       case 38 : parent.prevY(); break; \n"+
    		"       case 39 : parent.nextM(); break; \n"+
    		"       case 40 : parent.nextY(); break;\n"+
    		"       case 84 : document.forms[0].today.click(); break;\n"+
    		"   } window.event.keyCode = 0; window.event.returnValue= false;}\n"+
    		"function dragStart(){cx=window.event.clientX; cy=window.event.clientY; drag=true;}</scr"+"ipt>"}
    		strIframe += "<select name='tmpYearSelect'  onblur='parent.hiddenSelect(this)' style='z-index:1;position:absolute;top:3;left:18;display:none'"+
    		" onchange='parent.webCalendar.thisYear =this.value; parent.hiddenSelect(this); parent.writeCalendar();'></select>"+
    		"<select name=tmpMonthSelect onblur='parent.hiddenSelect(this)' style='z-index:1; position:absolute;top:3;left:74;display:none'"+
    		" onchange='parent.webCalendar.thisMonth=this.value; parent.hiddenSelect(this); parent.writeCalendar();'></select>"+
    		"<table id=tableMain class=bg border=0 cellspacing=2 cellpadding=0>"+
    		"<tr><td width=140 height=19 bgcolor='"+ webCalendar.lightColor +"'>"+
    		"    <table width=140 id=tableHead border=0 cellspacing=1 cellpadding=0><tr align=center>"+
    		"    <td width=15 height=19 class=bg title='向前翻 1 月&#13;快捷键：←' style='cursor: hand;bgcolor:#fd5d2b' onclick='parent.prevM()' ><b>&lt;</b></td>"+
    		"    <td width=60 id='meizzYearHead'  title='点击此处选择年份' onclick='parent.funYearSelect(parseInt(this.innerHTML, 10))'"+
    		"        onmouseover='this.bgColor=parent.webCalendar.darkColor; this.style.color=parent.webCalendar.lightColor'"+
    		"        onmouseout='this.bgColor=parent.webCalendar.lightColor; this.style.color=parent.webCalendar.wordColor'></td>"+
    		"    <td width=50 id=meizzYearMonth title='点击此处选择月份' onclick='parent.funMonthSelect(parseInt(this.innerHTML, 10))'"+
    		"        onmouseover='this.bgColor=parent.webCalendar.darkColor; this.style.color=parent.webCalendar.lightColor'"+
    		"        onmouseout='this.bgColor=parent.webCalendar.lightColor; this.style.color=parent.webCalendar.wordColor'></td>"+
    		"    <td width=15 class=bg title='向后翻 1 月&#13;快捷键：→' onclick='parent.nextM()' style='cursor: hand;bgcolor:#fd5d2b' ><b>&gt;</b></td></tr></table>"+
    		"</td></tr><tr><td height=20><table id=tableWeek border=0 width=140 cellpadding=0 cellspacing=0 ";
    		if(webCalendar.drag){
    			strIframe += "onmousedown='dragStart()' onmouseup='drag=false' onmouseout='drag=false'";
    		}
    		strIframe += " borderColorLight='"+ webCalendar.darkColor +"' borderColorDark='"+ webCalendar.lightColor +"'>"+
    		"    <tr align=center><td height=20>日</td><td>一</td><td>二</td><td>三</td><td>四</td><td>五</td><td>六</td></tr></table>"+
    		"</td></tr><tr><td valign=top width=140 bgcolor='"+ webCalendar.lightColor +"'>"+
    		"    <table id=tableDay height=120 width=140 border=0 cellspacing=1 cellpadding=0>";
         	for(var x=0; x<5; x++){ 
         		strIframe += "<tr>";
         		for(var y=0; y<7; y++)  
         			strIframe += "<td class=out id='meizzDay"+ (x*7+y) +"'></td>"; 
         		strIframe += "</tr>";
         	}
         	strIframe += "<tr>";
         	for(var x=35; x<39; x++) 
         		strIframe += "<td class=out id='meizzDay"+ x +"'></td>";
         	strIframe +="<td colspan=3 class=out><input style=' background-color: "+
         	webCalendar.btnBgColor +";cursor: hand; padding-top: 4px; width: 100%; height: 100%; border: 0' onfocus='this.blur()'"+
         	" type=button value='&nbsp;清空&nbsp;' onclick='parent.emptying()'></td></tr></table>"+
    		"</td></tr><tr><td height=20 width=140 bgcolor='"+ webCalendar.lightColor +"'>"+
    		"    <table cellpadding=1 cellspacing=0 width=140>"+
    		"    <tr><td><input style='width: 23' name=prevYear title='向前翻 1 年&#13;快捷键：↑' onclick='parent.prevY()' type=button value='&lt;&lt;'"+
    		"    onfocus='this.blur()' style='meizz:expression(this.disabled=parent.webCalendar.thisYear==1000)'><input  style='width: 19' "+
    		"    onfocus='this.blur()' name=prevMonth title='向前翻 1 月&#13;快捷键：←' onclick='parent.prevM()' type=button value='&lt;&nbsp;'>"+
    		"    </td><td align=center><input name=today type=button value='今天' onfocus='this.blur()' style='width: 50' title='当前日期&#13;快捷键：T'"+
    		"    onclick=\"parent.returnDate(event,new Date().getDate() +'/'+ (new Date().getMonth() +1) +'/'+ new Date().getFullYear())\">"+
    		"    </td><td align=right><input  style='width: 19'  title='向后翻 1 月&#13;快捷键：→' name=nextMonth onclick='parent.nextM()' type=button value='&nbsp;&gt;'"+
    		"    onfocus='this.blur()'><input  style='width: 23'  name=nextYear title='向后翻 1 年&#13;快捷键：↓' onclick='parent.nextY()' type=button value='&gt;&gt;'"+
    		"    onfocus='this.blur()' style='meizz:expression(this.disabled=parent.webCalendar.thisYear==9999)'></td></tr></table>"+
    		"</td></tr><table></form></body></html>";
           
   	with(webCalendar.iframe2){
       	document.writeln(strIframe); 
        document.close();
        for(var i=0; i<39; i++){
           	webCalendar.dayObj[i]=document.getElementById("meizzDay"+i);
           	webCalendar.dayObj[i].onmouseover = dayMouseOver;
           	webCalendar.dayObj[i].onmouseout  = dayMouseOut;
           	webCalendar.dayObj[i].onclick     = returnDate;
        }
    } 
}

function TWebCalendar(){
	//初始化日历的设置
    this.regInfo    = "WEB Calendar ver 1.0&#13;作者：briup&#13;网站：http://www.briup.com/&#13;关闭的快捷键：[Esc]";
    this.regInfo   += "&#13;&#13;Ver 2.0：briup&#13;Ver 1.0：briup";
    this.daysMonth  = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
    this.day        = new Array(39);            //定义日历展示用的数组
    this.dayObj     = new Array(39);            //定义日期展示控件数组
    this.dateStyle  = null;                     //保存格式化后日期数组
    this.objExport  = null;                     //日历回传的显示控件
    this.eventSrc   = null;                     //日历显示的触发控件
    this.inputDate  = null;                     //转化外的输入的日期(d/m/yyyy)
    this.thisYear   = new Date().getFullYear(); //定义年的变量的初始值
    this.thisMonth  = new Date().getMonth()+ 1; //定义月的变量的初始值
    this.thisDay    = new Date().getDate();     //定义日的变量的初始值
    this.today      = this.thisDay +"/"+ this.thisMonth +"/"+ this.thisYear;   //今天(d/m/yyyy)
    //日历的 iframe 载体
    this.iframe2='document.getElementById("meizzCalendarIframe").contentWindow.document';
    this.calendar   = getObjectById("meizzCalendarLayer");  //日历的层
    this.dateReg    = "";           //日历格式验证的正则式
    this.yearFall   = 50;           //定义年下拉框的年差值
    this.format2    = "yyyy-mm-dd"; //回传日期的格式
    this.timeShow   = false;        //是否返回时间
    this.drag       = true;         //是否允许拖动
    this.darkColor  = "#ff9900";    //控件的暗色
    this.lightColor = "#FFFFFF";    //控件的亮色
    this.btnBgColor = "#FFFFF5";    //控件的按钮背景色
    this.wordColor  = "#835a24";    //控件的文字颜色
    this.wordDark   = "#DCDCDC";    //控件的暗文字颜色
    this.dayBgColor = "#E6E6FA";    //日期数字背景色
    this.todayColor = "#FF0000";    //今天在日历上的标示背景色
    this.DarkBorder = "#D4D0C8";    //日期显示的立体表达色
}   


function funMonthSelect() //月份的下拉框
{
    var m = isNaN(parseInt(webCalendar.thisMonth, 10)) ? new Date().getMonth() + 1 : parseInt(webCalendar.thisMonth);
    var e = webCalendar.iframe2.document.forms[0].tmpMonthSelect;
    for (var i=1; i<13; i++) e.options.add(new Option(i +"月", i));
    e.style.display = ""; e.value = m; e.focus(); window.status = e.style.top;
}
function funYearSelect() //年份的下拉框
{
    var n = webCalendar.yearFall;
    var e = webCalendar.iframe2.document.forms[0].tmpYearSelect;
    var y = isNaN(parseInt(webCalendar.thisYear, 10)) ? new Date().getFullYear() : parseInt(webCalendar.thisYear);
        y = (y <= 1000)? 1000 : ((y >= 9999)? 9999 : y);
    var min = (y - n >= 1000) ? y - n : 1000;
    var max = (y + n <= 9999) ? y + n : 9999;
        min = (max == 9999) ? max-n*2 : min;
        max = (min == 1000) ? min+n*2 : max;
    for (var i=min; i<=max; i++){
    	e.options[e.options.length] = new Option(i +"年", i+"", true, true);//e.options.add(new Option(i +"年", i));
    }
    e.style.display = "";
    e.value = y; e.focus();
}

//往前翻月份
function prevM()  
{
    webCalendar.thisDay = 1;
    if (webCalendar.thisMonth==1){
        webCalendar.thisYear--;
        webCalendar.thisMonth=13;
    }
    webCalendar.thisMonth--; 
    writeCalendar();
}

//往后翻月份
function nextM()  
{
    webCalendar.thisDay = 1;
    if (webCalendar.thisMonth==12){
        webCalendar.thisYear++;
        webCalendar.thisMonth=0;
    }
    webCalendar.thisMonth++; writeCalendar();
}

//往前翻 Year
function prevY(){
	webCalendar.thisDay = 1;
	webCalendar.thisYear--; 
	writeCalendar();
}

//往后翻 Year
function nextY(){
	webCalendar.thisDay = 1; 
	webCalendar.thisYear++; 
	writeCalendar();
}
function hiddenSelect(e){
	for(var i=e.options.length; i>-1; i--)
		e.remove(i);
	e.style.display="none";
}

function getObjectById(id){
	return(document.getElementById(id));
	return(eval(id)); 
}
function hiddenCalendar(){
	getObjectById("meizzCalendarLayer").style.display = "none";
};
//日期自动补零程序
function appendZero(n){
	return(("00"+ n).substr(("00"+ n).length-2));
}
String.prototype.trim=function()
{
    return this.replace(/(^\s*)|(\s*$)/g,"");
}
function dayMouseOver()
{
    this.className = "over";
    this.style.backgroundColor = webCalendar.darkColor;
    if(webCalendar.day[this.id.substr(8)].split("/")[1] == webCalendar.thisMonth)
    this.style.color = webCalendar.lightColor;
}
function dayMouseOut()
{
    this.className = "out"; var d = webCalendar.day[this.id.substr(8)], a = d.split("/");
    this.style.backgroundColor='';
    if(a[1] == webCalendar.thisMonth && d != webCalendar.today){
        if(webCalendar.dateStyle && a[0] == parseInt(webCalendar.dateStyle[4], 10))
        this.style.color = webCalendar.lightColor;
        this.style.color = webCalendar.wordColor;
    }
}
//对日历显示的数据的处理程序
function writeCalendar(){
    var y = webCalendar.thisYear;
    var m = webCalendar.thisMonth; 
    var d = webCalendar.thisDay;
    webCalendar.daysMonth[1] = (0==y%4 && (y%100!=0 || y%400==0)) ? 29 : 28;
    if (!(y<=9999 && y >= 1000 && parseInt(m, 10)>0 && parseInt(m, 10)<13 && parseInt(d, 10)>0)){
        alert("对不起，你输入了错误的日期！");
        webCalendar.thisYear   = new Date().getFullYear();
        webCalendar.thisMonth  = new Date().getMonth()+ 1;
        webCalendar.thisDay    = new Date().getDate(); 
    }
    y = webCalendar.thisYear;
   	m = webCalendar.thisMonth;
    d = webCalendar.thisDay;

    webCalendar.iframe2.document.getElementById("meizzYearHead").innerHTML  = y +" 年";
    webCalendar.iframe2.document.getElementById("meizzYearMonth").innerHTML  = parseInt(m, 10) +" 月";
    
    webCalendar.daysMonth[1] = (0==y%4 && (y%100!=0 || y%400==0)) ? 29 : 28; //闰年二月为29天
    var w = new Date(y, m-1, 1).getDay();
    var prevDays = m==1  ? webCalendar.daysMonth[11] : webCalendar.daysMonth[m-2];
    //这三个 for 循环为日历赋数据源（数组 webCalendar.day）格式是 d/m/yyyy
    for(var i=(w-1); i>=0; i--){
       	webCalendar.day[i] = prevDays +"/"+ (parseInt(m, 10)-1) +"/"+ y;
       	if(m==1) 
       		webCalendar.day[i] = prevDays +"/"+ 12 +"/"+ (parseInt(y, 10)-1);
       	prevDays--;
   	}
   	for(var i=1; i<=webCalendar.daysMonth[m-1]; i++)
   		webCalendar.day[i+w-1] = i +"/"+ m +"/"+ y;
   	for(var i=1; i<39-w-webCalendar.daysMonth[m-1]+1; i++){
       	webCalendar.day[webCalendar.daysMonth[m-1]+w-1+i] = i +"/"+ (parseInt(m, 10)+1) +"/"+ y;
       	if(m==12) 
       		webCalendar.day[webCalendar.daysMonth[m-1]+w-1+i] = i +"/"+ 1 +"/"+ (parseInt(y, 10)+1);
   	}
    
   	//这个循环是根据源数组写到日历里显示
   	for(var i=0; i<39; i++){
       	var a = webCalendar.day[i].split("/");
       	webCalendar.dayObj[i].innerHTML    = a[0];
       	webCalendar.dayObj[i].title        = a[2] +"-"+ appendZero(a[1]) +"-"+ appendZero(a[0]);
       	webCalendar.dayObj[i].bgColor      = webCalendar.dayBgColor;
       	webCalendar.dayObj[i].style.color  = webCalendar.wordColor;
       	if ((i<10 && parseInt(webCalendar.day[i], 10)>20) || (i>27 && parseInt(webCalendar.day[i], 10)<12))
           	webCalendar.dayObj[i].style.color = webCalendar.wordDark;
       	//设置输入框里的日期在日历上的颜色
       	if (webCalendar.inputDate==webCalendar.day[i]){
       		webCalendar.dayObj[i].bgColor = webCalendar.darkColor; 
       		webCalendar.dayObj[i].style.color = webCalendar.lightColor;
       	}
       	//设置今天在日历上反应出来的颜色
       	if (webCalendar.day[i] == webCalendar.today){
        	webCalendar.dayObj[i].bgColor = webCalendar.todayColor; 
       		webCalendar.dayObj[i].style.color = webCalendar.lightColor;
       	}
   	}
}

//根据日期格式等返回用户选定的日期
function returnDate() 
{
    if(webCalendar.objExport)
    {
        var returnValue;
		var a;
		if(Browser.isIE){
			if(arguments.length==0){
				a=webCalendar.day[this.id.substr(8)].split("/");
			}else{
				a=arguments[1].split("/");
			}
		} else{
			if(arguments.length==1){
				a=webCalendar.day[this.id.substr(8)].split("/");
			}else{
				a=arguments[1].split("/");
			}
		}      

        var d = webCalendar.format2.match(/^(\w{4})(-|\/|.|)(\w{1,2})\2(\w{1,2})$/);
        if(d==null){alert("你设定的日期输出格式不对！\r\n\r\n请重新定义 webCalendar.format2 ！"); return false;}
        var flag = d[3].length==2 || d[4].length==2; //判断返回的日期格式是否要补零
        returnValue = flag ? a[2] +d[2]+ appendZero(a[1]) +d[2]+ appendZero(a[0]) : a[2] +d[2]+ a[1] +d[2]+ a[0];
        if(webCalendar.timeShow)
        {
            var h = new Date().getHours(), m = new Date().getMinutes(), s = new Date().getSeconds();
            returnValue += flag ? " "+ appendZero(h) +":"+ appendZero(m) +":"+ appendZero(s) : " "+  h  +":"+ m +":"+ s;
        }
        webCalendar.objExport.value = returnValue;
        hiddenCalendar();
    }
}

//清空
function emptying()
{
    if(webCalendar.objExport)
    {
        webCalendar.objExport.value = "";
        hiddenCalendar();
    }
}

document.onclick=function(e)
{
    //alert(webCalendar.eventSrc!= window.event.srcElement);
    //if(webCalendar.eventSrc !=Browser.isIE?window.event.srcElement:e.target) 
    if(webCalendar.eventSrc !=(Browser.isIE?window.event.srcElement:e.target)) 
        hiddenCalendar();
}

//-->

