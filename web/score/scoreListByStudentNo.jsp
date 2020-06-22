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
    <div id="location">
        课程安排管理 --> 课程安排信息
    </div>
    <div id="criteriaQueries">
        <h4>请选择查询条件：</h4>
        <form  action="${pageContext.request.contextPath}/CheckScoreServlet" method="post">
            <div class="queryinput">
                安排课程：<label>
                <select name="courseNo">
                    <option value="">请选择课程...</option>
                    <c:forEach var="courseList" items="${requestScope.courseList}" >
                        <c:choose>
                            <c:when test="${courseList.courseNo==requestScope.courseNo}">
                                <option value="${courseList.courseNo}" selected>${courseList.courseName}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${courseList.courseNo}">${courseList.courseName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </label>
            </div>
            <div class="queryinput">
                教师编号：<input type="text" name="teacherNo" value="${requestScope.teacherNo}" size="10"/>
            </div>
            <div class="queryinput">
                教师姓名：<input type="text" name="teacherName" value="${requestScope.teacherName}" size="5"/>
            </div>
            <div class="queryinput">
                成绩范围：<input type="text" name="scoreMin" value="${requestScope.scoreMin}" size="1"/>~<input type="text" name="scoreMax" value="${requestScope.scoreMax}" size="1"/>
            </div>
            <div class="queryinput">
                <input type="submit" value="查询" style="margin-left: 20px"/>
            </div>
        </form>
    </div>
    <div id="listAllMessage">
        <h3>信息列表</h3>
        <table style="margin: 0 auto;margin-top: 20px;border-collapse: collapse;">
            <tr>
                <td>id</td>
                <td>安排课程</td>
                <td>课程学分</td>
                <td>开课学期</td>
                <td>任课教师</td>
                <td>上课地点</td>
                <td>成绩</td>
            </tr>
            <c:forEach var="listAllScore" items="${requestScope.list}" >
                <tr>
                    <td>${listAllScore.id}</td>
                    <td>${listAllScore.courseArrange.course.courseName}</td>
                    <td>${listAllScore.courseArrange.course.grade}</td>
                    <td>${listAllScore.courseArrange.course.term}</td>
                    <td>${listAllScore.courseArrange.teacher.teacherName}</td>
                    <td>${listAllScore.courseArrange.studyRoom}</td>
                    <c:if test="${listAllScore.score!=null}">
                        <td>${listAllScore.score}</td>
                    </c:if>
                    <c:if test="${listAllScore.score==null}">
                        <td>未考试</td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <div id="pageButton">
            <c:if test="${requestScope.curPage>1}">
                <c:if test="${requestScope.courseNo!=null||requestScope.classNo!=null||requestScope.teacherNo!=null||requestScope.teacherName!=null||requestScope.scoreMin!=null||requestScope.scoreMax!=null}">
                    <a href="${pageContext.request.contextPath}\CheckScoreServlet?curPage=1&courseNo=${requestScope.courseNo}&classNo=${requestScope.classNo}&teacherNo=${requestScope.teacherNo}&teacherName=${requestScope.teacherName}&studentNo=${requestScope.studentNo}&name=${requestScope.name}&scoreMin=${requestScope.scoreMin}&scoreMax=${requestScope.scoreMax}" >首页</a>
                    <a href="${pageContext.request.contextPath}\CheckScoreServlet?curPage=${requestScope.curPage-1}&courseNo=${requestScope.courseNo}&classNo=${requestScope.classNo}&teacherNo=${requestScope.teacherNo}&teacherName=${requestScope.teacherName}&studentNo=${requestScope.studentNo}&name=${requestScope.name}&scoreMin=${requestScope.scoreMin}&scoreMax=${requestScope.scoreMax}" >上一页</a>
                </c:if>
                <c:if test="${requestScope.courseNo==null&&requestScope.classNo==null&&requestScope.teacherNo==null&&requestScope.teacherName==null&&requestScope.scoreMin==null&&requestScope.scoreMax==null}">
                    <a href="${pageContext.request.contextPath}\ListAllScoreServlet?curPage=1">首页</a>
                    <a href="${pageContext.request.contextPath}\ListAllScoreServlet?curPage=${requestScope.curPage-1}">上一页</a>
                </c:if>
            </c:if>
            <c:if test="${requestScope.curPage==1}">
                <a style="color: #909090">首页</a>
                <a style="color: #909090">上一页</a>
            </c:if>
            <font color="red">${requestScope.curPage}</font>
            <c:if test="${requestScope.curPage<requestScope.pageCount}">
                <c:if test="${requestScope.courseNo!=null||requestScope.classNo!=null||requestScope.teacherNo!=null||requestScope.teacherName!=null||requestScope.scoreMin!=null||requestScope.scoreMax!=null}">
                    <a href="${pageContext.request.contextPath}\CheckScoreServlet?curPage=${requestScope.curPage+1}&courseNo=${requestScope.courseNo}&classNo=${requestScope.classNo}&teacherNo=${requestScope.teacherNo}&teacherName=${requestScope.teacherName}&studentNo=${requestScope.studentNo}&name=${requestScope.name}&scoreMin=${requestScope.scoreMin}&scoreMax=${requestScope.scoreMax}" >下一页</a>
                    <a href="${pageContext.request.contextPath}\CheckScoreServlet?curPage=${requestScope.pageCount}&courseNo=${requestScope.courseNo}&classNo=${requestScope.classNo}&teacherNo=${requestScope.teacherNo}&teacherName=${requestScope.teacherName}&studentNo=${requestScope.studentNo}&name=${requestScope.name}&scoreMin=${requestScope.scoreMin}&scoreMax=${requestScope.scoreMax}" >尾页</a>
                </c:if>
                <c:if test="${requestScope.courseNo==null&&requestScope.classNo==null&&requestScope.teacherNo==null&&requestScope.teacherName==null&&requestScope.scoreMin==null&&requestScope.scoreMax==null}">
                    <a href="${pageContext.request.contextPath}\ListAllScoreServlet?curPage=${requestScope.curPage+1}">下一页</a>
                    <a href="${pageContext.request.contextPath}\ListAllScoreServlet?curPage=${requestScope.pageCount}">尾页</a>
                </c:if>
            </c:if>
            <c:if test="${requestScope.curPage==requestScope.pageCount}">
                <a style="color: #909090">下一页</a>
                <a style="color: #909090">尾页</a>
            </c:if>
            <font >总页数  ${requestScope.pageCount}</font>
        </div

    </div>
</div>
<div id="footer" style="text-align: center;">
    <span align="center">CopyRight&copy;2020</span>
    <span>理工学院软件工程专业 </span>
</div>
</body>
</html>

