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
    <form action="${pageContext.request.contextPath}\UpdateCourseArrangeServlet" method="post">
        <table style="border-collapse: collapse;margin: 0 auto; ">
            <tr>
                <td class="td1">课程安排编号</td>
                <td class="td2">
                    <input disabled="disabled" type="text" name="arrangeNo" value="${requestScope.courseArrange.arrangeNo}"/>
                    <input type="hidden" name="arrangeNo" value="${requestScope.courseArrange.arrangeNo}"/>
                </td>

            </tr>
            <tr>
                <td class="td1">安排课程</td>
                <td class="td2">
                    <select name="courseNo">
                        <option value="">请选择课程...</option>
                        <c:forEach var="courseList" items="${requestScope.courseList}">
                            <c:choose>
                                <c:when test="${courseList.courseNo==requestScope.courseArrange.course.courseNo}">
                                    <option value="${courseList.courseNo}" selected>${courseList.courseName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${courseList.courseNo}">${courseList.courseName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="td1">上课班级</td>
                <td class="td2">
                    <select name="classNo">
                        <option value="">请选择班级...</option>
                        <c:forEach var="classList" items="${requestScope.classList}">
                            <c:choose>
                                <c:when test="${classList.classNo==requestScope.courseArrange.classtbl.classNo}">
                                    <option value="${classList.classNo}" selected>${classList.className}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${classList.classNo}">${classList.className}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="td1">任课教师</td>
                <td class="td2">
                    <select name="teacherNo">
                        <option value="">请选择教师</option>
                        <c:forEach var="teacherList" items="${requestScope.teacherList}">
                            <c:choose>
                                <c:when test="${teacherList.teacherNo==requestScope.courseArrange.teacher.teacherNo}">
                                    <option value="${teacherList.teacherNo}" selected>${teacherList.teacherName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${teacherList.teacherNo}">${teacherList.teacherName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="td1">上课地点</td>
                <td class="td2"><input name="studyRoom" value="${requestScope.courseArrange.studyRoom}"/></td>
            </tr>
        </table>
        <div>
            <input type="submit" value="提交" id="submitButton"/>
        </div>
    </form>
</div>
<div id="footer" style="text-align: center;">
    <span align="center">CopyRight&copy;2020</span>
    <span>理工学院软件工程专业 </span>
</div>
</body>
</html>

