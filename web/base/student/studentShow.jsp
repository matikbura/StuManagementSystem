<%@ page import="listener.CountListener" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>studentShow</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css"/>
</head>

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
			<p id="">学生管理 - 学生信息</p>
		</div>
		<div id="mainContent">
			<form action="UpdateStudentServlet" method="post" name="myForm">
				<table style="margin: 0 auto; padding:30px 0 0 10px;border-collapse: collapse;"><tbody>
					<tr>
						<td align="right" class="iput">学生信息</td>
						<td class="dataIput">注：带<font color="red"> * </font>号的必须填写</td>
					</tr>
					<tr>
						<td align="right" class="iput">学生学号:</td>
						<td class="dataIput"><input disabled="disabled" name="studentNo" value="${requestScope.student.studentNo }"/><font color="red"> * </font></td>
						<input type="hidden" name="studentNo" value="${requestScope.student.studentNo }"/>
					</tr>
					<tr>
						<td align="right" class="iput">教师姓名:</td>
						<td class="dataIput"><input id="name" name="name" value="${requestScope.student.name }"/><font color="red"> * </font></td>
					</tr>
					<tr>
						<td align="right" class="iput">密码:</td>
						<td class="dataIput"><input id="password" name="password"  value="${requestScope.student.password }"/><font color="red"> * </font></td>
					</tr>
					<tr>
						<td align="right" class="iput">家庭住址:</td>
						<td style="width: 480px" class="dataIput"><input  id="address" name="address" size=60  value="${requestScope.student.address }"/><font color="red"> * </font></td>
					</tr>
					<tr>
						<td align="right" class="iput">电话:</td>
						<td class="dataIput"><input id="phone" name="phone"  value="${requestScope.student.phone }"/><font color="red"> * </font></td>
					</tr>
					<tr>
						<td align="right" class="iput">EMAIL:</td>
						<td class="dataIput"><input id="email" name="email"  value="${requestScope.student.email }"/><font color="red"> * </font></td>
					</tr>
					<tr>
						<td align="right" class="iput">班级号：</td>
						<td class="dataIput">
							<select name="classNo">
								<c:forEach var="chooseClass" items="${sessionScope.classList}">
									<c:choose>
										<c:when test="${requestScope.student.classNo==chooseClass.classNo}">
											<option value="${chooseClass.classNo}" selected>${chooseClass.className}</option>
										</c:when>
										<c:otherwise>
											<option value="${chooseClass.classNo}">${chooseClass.className}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td id="addButton">
							<input type="submit" value="修改" style="margin: 0 auto" onclick="return check()">
							<a href="ListAllStudentServlet">返回</a>
						</td>
					</tr>
				</tbody></table>
			</form>

		</div>

	</div>
</div>

		</div>

	</div>
</div>
<div id="footer" style="text-align: center;">
	<span align="center">CopyRight&copy;2020</span>
	<span>理工学院软件工程专业 </span>
</div>
</body>
</html>
