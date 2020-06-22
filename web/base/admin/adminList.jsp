<%@ page import="listener.CountListener" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>adminList</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css"/>
		<style type="text/css">
			tr td{
				width: 280px;
				border: solid 1px black;
			}
		</style>
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
					<p id="">管理员维护</p>
				</div>
				<div id="mainContent">
					<form action="CheckAdminServlet" method="post">
						<div id="techerquerymessage" style="padding: 10px">
							<h4 >查询管理员</h4>
							<div class="techerquerymessageaa">
								<c:if test="${requestScope.loginName!=null}">
									管理员登录名:<input  name="loginName" value="${requestScope.loginName}"/>
								</c:if>
								<c:if test="${requestScope.loginName==null}">
									管理员登录名:<input  name="loginName" />
								</c:if>

							</div>
							<div class="techerquerymessageaa">
								<c:if test="${requestScope.name!=null}">
									管理员姓名:<input  name="name" value="${requestScope.name}"/>
								</c:if>
								<c:if test="${requestScope.name==null}">
									管理员姓名:<input  name="name" />
								</c:if>
							</div>
							<div style="text-align: center;">
								<input type="submit" value="查询"/>
								<input type="reset" value="重置"/>
							</div>
						</div>
						<div id="listAllMessage" style="text-align: center;position:relative;">
							<h3 style="padding-top: 30px">信息列表</h3>
								<table style="border-collapse: collapse;margin:0 auto;margin-top: 20px;margin-bottom: 20px">
									<tr>
										<td>管理员登录名</td>
										<td>管理员姓名</td>
										<td>操作</td>
									</tr>
									<c:forEach  var="adminmessage" items="${requestScope.list}" >
									<tr>
										<td>${adminmessage.loginName}</td>
										<td>${adminmessage.name}</td>
										<td><a href="ShowAdminServlet?loginName=${adminmessage.loginName}">查看</a>
											<a href="DeleteAdminServlet?loginName=${adminmessage.loginName}">删除</a></td>
									</tr>
									</c:forEach>
								</table>
							<a href="${pageContext.request.contextPath}/base/admin/addAdmin.jsp" style="margin: 30px 0 10px 630px;" id="addButton2">新增</a>
							<div id="pageButton">
								<c:if test="${requestScope.curPage>1}">
										<c:if test="${requestScope.name!=null||requestScope.loginName!=null}">
											<a href="CheckAdminServlet?curPage=1&loginName=${requestScope.loginName}$name=${requestScope.name}">首页</a>
											<a href="CheckAdminServlet?curPage=${requestScope.curPage-1}&loginName=${requestScope.loginName}&name=${requestScope.name}">上一页</a>
										</c:if>
										<c:if test="${requestScope.name==null}">
											<a href="ListAllAdminServlet?curPage=1"&loginName="${requestScope.loginName}">首页</a>
											<a href="ListAllAdminServlet?curPage=${requestScope.curPage-1}&loginName="${requestScope.loginName}">上一页</a>
										</c:if>
								</c:if>
								<c:if test="${requestScope.curPage<=1}">
									<a style="color: gray">首页</a>
									<a style="color: gray">上一页</a>
								</c:if>
								<font color="red">${requestScope.curPage}</font>
								<c:if test="${requestScope.curPage<requestScope.pageCount}">
										<c:if test="${requestScope.name!=null||requestScope.loginName!=null}">
											<a href="CheckAdminServlet?curPage=${requestScope.curPage+1}&loginName=${requestScope.loginName}&name=${requestScope.name}">下一页</a>
											<a href="CheckAdminServlet?curPage=${requestScope.pageCount}&loginName=${requestScope.loginName}&name=${requestScope.name}">尾页</a>
										</c:if>
										<c:if test="${requestScope.name==null&&requestScope.loginName==null}">
											<a href="ListAllAdminServlet?curPage=${requestScope.curPage+1}">下一页</a>
											<a href="ListAllAdminServlet?curPage=${requestScope.pageCount}">尾页</a>
										</c:if>
								</c:if>
								<c:if test="${requestScope.curPage>=requestScope.pageCount}">
									<a style="color: gray">下一页</a>
									<a style="color: gray">尾页</a>
								</c:if>
								<font >总页数  ${requestScope.pageCount}</font>
							</div>

						</div>
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
