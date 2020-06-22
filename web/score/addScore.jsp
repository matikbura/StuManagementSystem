<%@ page import="listener.CountListener" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>addAdmin</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base2.css"/>
    <style type="text/css">
        .td1{
            width: 120px;
            height: 15px;
            border: solid 1px black;
        }
        .td2{
            width: 200px;
            height: 25px;
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
<div id="location" style="width:200px; margin: 0 auto;margin-top: 20px ">
    课程安排管理 --> 添加课程安排
</div>
<div id="addContent">
    <form action="${pageContext.request.contextPath}\AddScoreServlet" method="post">
        <table style="border-collapse: collapse;margin: 0 auto; ">
            <tr>
                <td class="td1">学生姓名</td>
                <td class="td2">
                    ，<label>
                    <select name="studentNo">
                    <option value="">请选择学生...</option>
                    <c:forEach items="${requestScope.studentList}" var="studentList">
                        <option value="${studentList.studentNo}">${studentList.name}</option>
                    </c:forEach>
                </select>
                </label>
                </td>
            </tr>
            <tr>
                <td class="td1">课程安排编号</td>
                <td class="td2">
                    <label>
                        <select name="arrangeNo">
                            <option value="">请选择编号...</option>
                            <c:forEach var="courseArrangeList" items="${requestScope.courseArrangeList}">
                                <option value="${courseArrangeList.arrangeNo}">${courseArrangeList.arrangeNo}</option>
                            </c:forEach>
                        </select>
                    </label>
                </td>
            </tr>
            <tr>
                <td class="td1">成绩：</td>
                <td class="td2"><input type="text" size="3" name="score"/></td>
            </tr>
        </table>
        <div id ="submitButton">
            <input type="submit" value="提交"/>
        </div>
    </form>
</div>
<div id="footer" style="text-align: center;">
    <span align="center">CopyRight&copy;2020</span>
    <span>理工学院软件工程专业 </span>
</div>
</body>
</html>

