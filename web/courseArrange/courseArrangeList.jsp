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
        <form  action="${pageContext.request.contextPath}/CheckCourseArrangeServlet" method="post">
            课程安排编号:<select name="arrangeNo">
                <option >请选择课程编号</option>
                <c:forEach var="chooseArrange" items="${requestScope.courseArrangeList}">
                    <c:choose>
                        <c:when test="${chooseArrange.arrangeNo==requestScope.arrangeNo}">
                            <option value="${chooseArrange.arrangeNo}" selected>${chooseArrange.arrangeNo}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${chooseArrange.arrangeNo}">${chooseArrange.arrangeNo}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            安排课程:<select name="courseNo">
                <option >请选择课程</option>
                <c:forEach var="chooseCourse" items="${requestScope.courseList}">
                    <c:choose>
                        <c:when test="${chooseCourse.courseNo==requestScope.courseNo}">
                            <option value="${chooseCourse.courseNo}" selected>${chooseCourse.courseName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${chooseCourse.courseNo}">${chooseCourse.courseName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            上课班级:<select name="classNo">
                <option >请选择班级</option>
                <c:forEach var="chooseClass" items="${requestScope.classList}">
                    <c:choose>
                        <c:when test="${chooseClass.classNo==requestScope.classNo}">
                            <option value="${chooseClass.classNo}" selected>${chooseClass.className}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${chooseClass.classNo}">${chooseClass.className}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            任课教师:<select name="teacherNo">
                <option >请选择教师</option>
                <c:forEach var="chooseTeacher" items="${requestScope.teacherList}">
                    <c:choose>
                        <c:when test="${chooseTeacher.teacherNo==requestScope.teacherNo}">
                            <option value="${chooseTeacher.teacherNo}" selected>${chooseTeacher.teacherName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${chooseTeacher.teacherNo}">${chooseTeacher.teacherName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <input type="submit" value="查询"/>
        </form>
    </div>
    <div id="listAllMessage">
        <h3>信息列表</h3>
        <table style="margin: 0 auto;margin-top: 20px;border-collapse: collapse;">
            <tr>
                <td>课程安排编号</td>
                <td>安排课程</td>
                <td>上课班级</td>
                <td>任课教师</td>
                <td>上课地点</td>
                <td>操作</td>
            </tr>
            <c:forEach var="listAllCourseArrange" items="${requestScope.list}" >
                <tr>
                    <td>${listAllCourseArrange.arrangeNo}</td>
                    <td>${listAllCourseArrange.course.courseName}</td>
                    <td>${listAllCourseArrange.classtbl.className}</td>
                    <td>${listAllCourseArrange.teacher.teacherName}</td>
                    <td>${listAllCourseArrange.studyRoom}</td>
                    <td>
                        <a href="ShowCourseArrangeServlet?arrangeNo=${listAllCourseArrange.arrangeNo}">查看</a>
                        <a href="DeleteCourseArrangeServlet?arrangeNo=${listAllCourseArrange.arrangeNo}">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="PreAddCourseArrangeServlet" style="margin: 30px 0 10px 630px;" id="addButton2">新增</a>
        <div id="pageButton">
            <c:if test="${requestScope.curPage>1}">
                <c:if test="${requestScope.arrangeNo!=null||requestScope.courseNo!=null||requestScope.classNo!=null
                ||requestScope.teacherNo!=null}">
                    <a href="${pageContext.request.contextPath}\CheckCourseArrangeServlet?curPage=1&arrangeNo=${requestScope.arrangeNo}&courseNo=${requestScope.courseNo}&classNo=${requestScope.classNo}&
                     teacherNo=${requestScope.teacherNo}" >首页</a>
                    <a href="${pageContext.request.contextPath}\CheckCourseArrangeServlet?curPage=${requestScope.curPage-1}&arrangeNo=${requestScope.arrangeNo}&courseNo=${requestScope.courseNo}&classNo=${requestScope.classNo}&
                     teacherNo=${requestScope.teacherNo}" >上一页</a>
                </c:if>
                <c:if test="${requestScope.arrangeNo==null&&requestScope.courseNo==null&&
                requestScope.classNo==null&&requestScope.teacherNo==null}">
                    <a href="${pageContext.request.contextPath}\ListAllCourseArrangeServlet?curPage=1">首页</a>
                    <a href="${pageContext.request.contextPath}\ListAllCourseArrangeServlet?curPage=${requestScope.curPage-1}">上一页</a>
                </c:if>
            </c:if>
            <c:if test="${requestScope.curPage==1}">
                <a style="color: #909090">首页</a>
                <a style="color: #909090">上一页</a>
            </c:if>
            <font color="red">${requestScope.curPage}</font>
            <c:if test="${requestScope.curPage<requestScope.pageCount}">
                <c:if test="${requestScope.arrangeNo!=null||requestScope.courseNo!=null||requestScope.classNo!=null
                ||requestScope.teacherNo!=null}">
                    <a href="${pageContext.request.contextPath}\CheckCourseArrangeServlet?curPage=${requestScope.curPage+1}&arrangeNo=${requestScope.arrangeNo}&courseNo=${requestScope.courseNo}&classNo=${requestScope.classNo}&
                     teacherNo=${requestScope.teacherNo}" >下一页</a>
                    <a href="${pageContext.request.contextPath}\CheckCourseArrangeServlet?curPage=${requestScope.pageCount}&arrangeNo=${requestScope.arrangeNo}&courseNo=${requestScope.courseNo}&classNo=${requestScope.classNo}&
                     teacherNo=${requestScope.teacherNo}" >尾页</a>
                </c:if>
                <c:if test="${requestScope.arrangeNo==null&&requestScope.courseNo==null&&
                requestScope.classNo==null&&requestScope.teacherNo==null}">
                    <a href="${pageContext.request.contextPath}\ListAllCourseArrangeServlet?curPage=${requestScope.curPage+1}">下一页</a>
                    <a href="${pageContext.request.contextPath}\ListAllCourseArrangeServlet?curPage=${requestScope.pageCount}">尾页</a>
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

