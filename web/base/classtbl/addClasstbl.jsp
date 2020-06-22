<%@ page import="listener.CountListener" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>addClasstbl</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css"/>
</head>
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
		if(isEmpty(document.myForm.teacherName.value)){
			alert("教师姓名不能为空！");
			document.myForm.teacherName.focus();
			return false;
		}
		if(isEmpty(document.myForm.password.value)){
			alert("密码不能为空！");
			document.myForm.password.focus();
			return false;
		}
		if(document.myForm.professional.value==""){
			alert("请选择教师职称！");
			document.myForm.professional.focus();
			return false;
		}
		if(isEmpty(document.myForm.education.value)){
			alert("学历不能为空！");
			document.myForm.education.focus();
			return false;
		}
		if(isEmpty(document.myForm.address.value)){
			alert("家庭住址不能为空！");
			document.myForm.address.focus();
			return false;
		}
		if(isEmpty(document.myForm.phone.value)){
			alert("电话不能为空！");
			document.myForm.phone.focus();
			return false;
		}
		if(isEmpty(document.myForm.email.value)){
			alert("email不能为空！");
			document.myForm.email.focus();
			return false;
		}
		if(!isEmail(document.myForm.email.value)){
			alert("email格式不正确！");
			document.myForm.email.focus();
			return false;
		}
		if(isEmpty(document.myForm.subject.value)){
			alert("研究方向不能为空！");
			document.myForm.subject.focus();
			return false;
		}
		return true;
	}
</script>
<c:if test="${sessionScope.message!=null}">
	<script type="text/javascript">
		alert("${sessionScope.message}");
	</script>
	<% session.removeAttribute("message");%>
</c:if>
<body>
<div id="head">
	<div id="headLeft">
		<ul>
			<li><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
			<li><a href="${pageContext.request.contextPath}/ListAllAdminServlet">基本数据管理</a></li>
			<li><a href="${pageContext.request.contextPath}/ListAllCourseArrangeServlet">课程安排管理</a></li>
			<li><a href="${pageContext.request.contextPath}/ListAllScoreServlet">学生成绩管理</a></li>
			<li><a href="${pageContext.request.contextPath}/ShowPersonalMessageServlet">个人信息管理</a></li>
		</ul>
	</div>
	<div id="headRight">
				<span id="loginMessage">
					<c:choose>
						<c:when test="${sessionScope.user.userType==0}">
							<font color=red>状态：已登录&nbsp;&nbsp;用户类别：管理员&nbsp;当前在线人数:<%=CountListener.getLinedNumber() %></font>
						</c:when>
						<c:when test="${sessionScope.user.userType==1}">
							<font color=red>状态：已登录&nbsp;&nbsp;用户类别：教师用户&nbsp;当前在线人数:<%=CountListener.getLinedNumber() %></font>
						</c:when>
						<c:when test="${sessionScope.user.userType==2}">
							<font color=red>状态：已登录&nbsp;&nbsp;用户类别：学生用户&nbsp;&nbsp;当前在线人数:<%=CountListener.getLinedNumber() %></font>
						</c:when>
						<c:otherwise>
							<font color=red>状态：未登录&nbsp;&nbsp;当前在线人数:<%=CountListener.getLinedNumber() %></font>
						</c:otherwise>
					</c:choose>
				</span>
	</div>
	<div class="loginAndExit">
		<c:if test="${sessionScope.user!=null}">
			<a href="exitServlet">注销</a>
		</c:if>
		<c:if test="${sessionScope.user==null}">
			<a href="${pageContext.request.contextPath}/login.jsp">登录</a>
		</c:if>
	</div>
</div>
<div id="content">
	<div id="contentleft">
		<div id="subfunction">
			<ul>
				<li><a href="${pageContext.request.contextPath}/ListAllAdminServlet" title="管理员维护"><span>管理员维护</span></a></li>
				<li><a href="${pageContext.request.contextPath}/ListAllStudentServlet" title="学生信息维护"><span>学生信息维护</span></a></li>
				<li><a href="${pageContext.request.contextPath}/ListAllTeacherServlet" title="教师信息维护"><span>教师信息维护</span></a></li>
				<li><a href="${pageContext.request.contextPath}/ListAllClasstblServlet" title="班级信息维护"><span>班级信息维护</span></a></li>
				<li><a href="${pageContext.request.contextPath}/ListAllCourseServlet" title="课程信息维护"><span>课程信息维护</span></a></li>
			</ul>
		</div>
	</div>
	<div id="contentright">
		<div id="location">
			<p id="">班级管理 - 增添班级</p>
		</div>
		<div id="mainContent">
			<form action="${pageContext.request.contextPath}/AddClasstblServlet" method="post" name="myForm">
				<table style="margin: 0 auto; padding:30px 0 0 10px;border-collapse: collapse;"><tbody>
				<tr class="dataIput">
					<td align="right" class="iput">班级信息</td>
					<td class="dataIput">注：带<font color="red" > * </font>号的必须填写</td>
				</tr>
				<tr>
					<td align="right" class="iput">班级编号:</td>
					<td class="dataIput"><input type="text" name="classNo" /><font color="red"> * </font></td>
				</tr>
				<tr>
					<td align="right" class="iput">班级名称:</td>
					<td class="dataIput"><input id="className" name="className"/><font color="red"> * </font></td>
				</tr>
				<tr>
					<td align="right" class="iput">所属学院:</td>
					<td class="dataIput"><input id="college" name="college"/><font color="red"> * </font></td>
				</tr>
					<td id="addButton">
						<input type="submit" value="增加" style="margin: 0 auto" onclick="return check()">
						<a href="${pageContext.request.contextPath}/ListAllClasstblServlet">返回</a>
					</td>
				</tr>
				</tbody></table>
			</form>

		</div>

	</div>
</div>
<div id="footer" style="text-align: center;">
	<span align="center">CopyRight&copy;2020</span>
	<span>理工学院软件工程专业 </span>
</div>
</body>
</html>
