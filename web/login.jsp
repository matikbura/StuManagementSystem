<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>学生成绩管理系统-用户登录</title>
	<link rel="stylesheet" type="text/css" href="css/index.css"/>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/stylesheet.css" rel="stylesheet">
	<style type="text/css">
		.ltd{
			position: absolute;
			width: 480px;
			height: 320px;
			left: 0;
			top: 0;
			right: 0;
			bottom: 0;
			margin: auto;
			padding-top: 20px;
			box-shadow:0px 0px 10px 0px rgba(50,50,50,0.8);
			border-radius: 10px;
			background-color: rgba(255,255,255,0.2);
			-webkit-backdrop-filter:saturate(170%) blur(10px);
			backdrop-filter: saturate(170%) blur(10px);
			text-shadow:0 0 0.4em lightgray,
			-0 -0 0.4em lightgray;
		}
		.ltdbtn{
			width:200px;
			border-radius:4px;
			BACKGROUND-COLOR: transparent;
			border:none;
			outline: none;
			border-bottom: solid 1px deepskyblue;
		}

		.submitBtn{
			margin-top: 30px;
			min-width: 80px;
			min-height: 80px;
			background: skyblue;
			border-radius: 40px;
			box-shadow: 0 0 0.5em white;
			color: white;
			border: none;
			outline: none;
			transition: .3s;
			cursor: pointer;
		}
		.submitBtn:hover{
			box-shadow: 0 0 1.5em white;
		}
		.submitBtn:active{
			transition: .1s;
			transform: scale(0.95);
		}
		#footer{
			position: absolute;
			left:43%;
			color: white;
			bottom: 0;
		}
	</style>
</head>
<script type="text/JavaScript">

	function MM_findObj(n, d) { //v4.01
		var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
			d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
		if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
		for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
		if(!x && d.getElementById) x=d.getElementById(n); return x;
	}

	function MM_swapImage() { //v3.0
		var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
			if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
	}
	//-->
</script>
<script type="text/JavaScript">
	/*判断是否为数字*/
	function isNumber(str) {
		var Letters = "1234567890";
		for (var i = 0; i < str.length; i = i + 1) {
			var CheckChar = str.charAt(i);
			if (Letters.indexOf(CheckChar) == -1) {
				return false;
			}
		}
		return true;
	}
	/*判断是否为Email*/
	function isEmail(str) {
		var myReg = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
		if (myReg.test(str)) {
			return true;
		}
		return false;
	}
	/*判断是否为空*/
	function isEmpty(value) {
		return /^\s*$/.test(value);
	}
	function check(){
		if(isEmpty(document.myForm.loginName.value)){
			alert("登录名不能为空！");
			document.myForm.loginName.focus();
			return false;
		}
		if(isEmpty(document.myForm.password.value)){
			alert("密码不能为空！");
			document.myForm.password.focus();
			return false;
		}
		return true;
	}
</script>
<c:if test="${!(empty sessionScope.message)}">
	<script type="text/javascript">
		alert('<c:out value="${sessionScope.message}"/>');
	</script>
	<c:remove var="message" scope="session"/>
</c:if>
<body id="particles-js">
       <div class="ltd" align="center" style="padding-top: 70px">
         <h2>用户登录系统</h2>	     
         <form name="myForm" action="LoginServlet" method="post"  style="margin-top: 20px">
				<div>
					<tr>
						<td width="30%" align="right">
							用户账号：
						</td>
						<td width="70%" align="left" >
							<input type="text" id="loginName" name="loginName" class="ltdbtn"/>
						</td>
					</tr>
				</div>
				<div style="padding-top: 10px">
							用户密码：
							<input type="password" id="password" name="password" class="ltdbtn" />
				</div>
			 <div>
				 <input type="submit" value="登录" onclick="return check();" class="submitBtn"/>
			 </div>

		  </form>
       </div>



  <div id="footer">
    <div id="copyright">
       <div id="copy" style="text-align: center">
       CopyRight&copy;2020理工学院软件工程专业
        </div>
    </div>
    <div id="bgbottom"></div>
  </div>

</div>
</div>
	   <script src="js/jquery-3.1.1.min.js"></script>
	   <script src="js/bootstrap.min.js"></script>
	   <script src="js/particles.min.js"></script>
	   <script src="js/app.js"></script>
</body>
</html>
