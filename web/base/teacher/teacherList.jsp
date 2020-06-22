<%@ page import="listener.CountListener" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<title>teacherList</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css"/>
	<style type="text/css">
		tr td{
			width: 150px;
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
					<p id="">教师管理</p>
				</div>
				<div id="mainContent">
					<form action="CheckTeacherServlet" method="post">
						<div id="techerquerymessage" style="padding: 10px">
							<h4 >查询教师</h4>
							<div class="techerquerymessageaa">
								教师编号:<input id="teacherNo" name="teacherNo" value="${requestScope.teacherNo}" />
							</div>
							<div class="techerquerymessageaa">
								教师姓名:<input id="teacherName" name="teacherName" value="${requestScope.teacherName}" />
							</div>
							<div class="techerquerymessageaa">
								职称:<select id="professional" name="professional" >
								<c:if test="${requestScope.professional==null}">
									<option value="" selected>请选择职称...</option>
									<option value="0" >教授</option>
									<option value="1">副教授</option>
									<option value="2">讲师</option>
									<option value="3">助教</option>
									<option value="4">其他</option>
								</c:if>
								<c:if test="${requestScope.professional==0}">
								<option value="">请选择职称...</option>
								<option value="0" selected>教授</option>
								<option value="1">副教授</option>
								<option value="2">讲师</option>
								<option value="3">助教</option>
								<option value="4">其他</option>
								</c:if>
								<c:if test="${requestScope.professional==1}">
								<option value="">请选择职称...</option>
								<option value="0">教授</option>
								<option value="1" selected>副教授</option>
								<option value="2">讲师</option>
								<option value="3">助教</option>
								<option value="4">其他</option>
								</c:if>
								<c:if test="${requestScope.professional==2}">
								<option value="">请选择职称...</option>
								<option value="0">教授</option>
								<option value="1">副教授</option>
								<option value="2" selected>讲师</option>
								<option value="3">助教</option>
								<option value="4">其他</option>
								</c:if>
								<c:if test="${requestScope.professional==3}">
								<option value="">请选择职称...</option>
								<option value="0">教授</option>
								<option value="1">副教授</option>
								<option value="2">讲师</option>
								<option value="3" selected>助教</option>
								<option value="4">其他</option>
								</c:if>
								<c:if test="${requestScope.professional==4}">
								<option value="">请选择职称...</option>
								<option value="0">教授</option>
								<option value="1">副教授</option>
								<option value="2">讲师</option>
								<option value="3">助教</option>
								<option value="4" selected>其他</option>
								</c:if>
							</select>
							</div>
							<div class="techerquerymessageaa">
								电话:<input id="phone" name="phone" value="${requestScope.phone}" />
							</div>
							<div class="techerquerymessageaa">
								EMAIL:<input id="email" name="email" value="${requestScope.email}" />
							</div>
							<div class="techerquerymessageaa">
								研究方向:<input id="subject" name="subject"  size="30" value="${requestScope.subject}"/>
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
										<td>教师编号</td>
										<td>教师名</td>
										<td>教师职称</td>
										<td>教师电话</td>
										<td>教师邮箱</td>
										<td>研究方向</td>
										<td>操作</td>
									</tr>
									<c:forEach  var="techermessage" items="${requestScope.list}" >
									<tr>
										<td>${techermessage.teacherNo}</td>
										<td>${techermessage.teacherName}</td>
										<c:if test="${techermessage.professional==0}">
											<td>教授</td>
										</c:if>
										<c:if test="${techermessage.professional==1}">
											<td>副教授</td>
										</c:if>
										<c:if test="${techermessage.professional==2}">
											<td>讲师</td>
										</c:if>
										<c:if test="${techermessage.professional==3}">
											<td>助教</td>
										</c:if>

										<td>${techermessage.phone}</td>
										<td>${techermessage.email}</td>
										<td>${techermessage.subject}</td>
										<td><a href="ShowTeacherServlet?teacherNo=${techermessage.teacherNo}">查看</a>
											<a href="DeleteTeacherServlet?teacherNo=${techermessage.teacherNo}">删除</a></td>
									</tr>
									</c:forEach>
								</table>
							<a href="base/teacher/addTeacher.jsp" style="margin: 30px 0 10px 630px;" id="addButton2">新增</a>
						</div>
							<div id="pageButton" >
								<c:if test="${requestScope.curPage>1}">
									<c:if test="${requestScope.teacherNo!=null||requestScope.teacherName!=null||requestScope.professional!=null
									||requestScope.phone!=null||requestScope.subject!=null||requestScope.email!=null}">
										<a href="CheckTeacherServlet?curPage=1&teacherNo=${requestScope.teacherNo}&teacherName=${requestScope.teacherName}
										&professional=${requestScope.professional}&phone=${requestScope.phone}&subject=${requestScope.subject}&email=${requestScope.email}">首页</a>
										<a href="CheckTeacherServlet?curPage=${requestScope.curPage-1}&teacherNo=${requestScope.teacherNo}&teacherName=${requestScope.teacherName}
										&professional=${requestScope.professional}&phone=${requestScope.phone}&subject=${requestScope.subject}&email=${requestScope.email}">上一页</a>
									</c:if>
									<c:if test="${requestScope.teacherNo==null&&requestScope.teacherName==null&&requestScope.professional==null
									&&requestScope.phone==null&&requestScope.subject==null&&requestScope.email==null}">
										<a href="ListAllTeacherServlet?curPage=1">首页</a>
										<a href="ListAllTeacherServlet?curPage=${requestScope.curPage-1}">上一页</a>
									</c:if>
								</c:if>
								<c:if test="${requestScope.curPage==1}">
									<a style="color: #909090">首页</a>
									<a style="color: #909090">上一页</a>
								</c:if>
								<font color="red">${requestScope.curPage}</font>
								<c:if test="${requestScope.curPage<requestScope.pageCount}">

									<c:if test="${requestScope.teacherNo!=null||requestScope.teacherName!=null||requestScope.professional!=null
									||requestScope.phone!=null||requestScope.subject!=null||requestScope.email!=null}">
										<a href="CheckTeacherServlet?curPage=${requestScope.curPage+1}&teacherNo=${requestScope.teacherNo}&teacherName=${requestScope.teacherName}
										&professional=${requestScope.professional}&phone=${requestScope.phone}&subject=${requestScope.subject}&email=${requestScope.email}">下一页</a>
										<a href="CheckTeacherServlet?curPage=${requestScope.pageCount}&teacherNo=${requestScope.teacherNo}&teacherName=${requestScope.teacherName}
										&professional=${requestScope.professional}&phone=${requestScope.phone}&subject=${requestScope.subject}&email=${requestScope.email}">尾页</a>
									</c:if>
									<c:if test="${requestScope.teacherNo==null&&requestScope.teacherName==null&&requestScope.professional==null
									&&requestScope.phone==null&&requestScope.subject==null&&requestScope.email==null}">
										<a href="ListAllTeacherServlet?curPage=${requestScope.curPage+1}">下一页</a>
										<a href="ListAllTeacherServlet?curPage=${requestScope.pageCount}">尾页</a>
									</c:if>
								</c:if>
								<c:if test="${requestScope.curPage==requestScope.pageCount}">
									<a style="color: #909090">下一页</a>
									<a style="color: #909090">尾页</a>
								</c:if>
								<font >总页数  ${requestScope.pageCount}</font>
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
