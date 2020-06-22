<%@ page import="listener.CountListener" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>adminShow</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/person.css">
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

    function check() {
        if (isEmpty(document.myForm.teacherName.value)) {
            alert("教师姓名不能为空！");
            document.myForm.teacherName.focus();
            return false;
        }
        if (isEmpty(document.myForm.password.value)) {
            alert("密码不能为空！");
            document.myForm.password.focus();
            return false;
        }
        if (document.myForm.professional.value == "") {
            alert("请选择教师职称！");
            document.myForm.professional.focus();
            return false;
        }
        if (isEmpty(document.myForm.education.value)) {
            alert("学历不能为空！");
            document.myForm.education.focus();
            return false;
        }
        if (isEmpty(document.myForm.address.value)) {
            alert("家庭住址不能为空！");
            document.myForm.address.focus();
            return false;
        }
        if (isEmpty(document.myForm.phone.value)) {
            alert("电话不能为空！");
            document.myForm.phone.focus();
            return false;
        }
        if (isEmpty(document.myForm.email.value)) {
            alert("email不能为空！");
            document.myForm.email.focus();
            return false;
        }
        if (!isEmail(document.myForm.email.value)) {
            alert("email格式不正确！");
            document.myForm.email.focus();
            return false;
        }
        if (isEmpty(document.myForm.subject.value)) {
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
    <div id="contentright">
        <div id="location" >
            <h1>个人信息</h1>
        </div>
        <div id="mainContent">
            <form action="UpdateAdminServlet" method="post" name="myForm">
                <table style="padding:30px 0 0 10px;border-collapse: collapse;text-align: center;margin: 0 auto">
                    <tbody>
                    <tr>
                        <td align="right"></td>
                        <td class="dataIput" style="font-size: 12px;">注：带<font color="red"> * </font>号的必须填写</td>
                    </tr>
                    <tr>
                        <td align="right" class="iput">管理员账号:</td>
                        <td class="dataIput"><input disabled="disabled" name="loginName"
                                                    value="${requestScope.admin.loginName }"/><font color="red">
                            * </font></td>
                        <input type="hidden" name="loginName" value="${requestScope.admin.loginName }"/>
                    </tr>
                    <tr>
                        <td align="right" class="iput">管理员姓名:</td>
                        <td class="dataIput"><input id="name" name="name" value="${requestScope.admin.name }"/><font
                                color="red"> * </font></td>
                    </tr>
                    <tr>
                        <td align="right" class="iput">密码:</td>
                        <td class="dataIput"><input id="password" name="password"
                                                    value="${requestScope.admin.password}"/><font color="red"> * </font>
                        </td>
                    </tr>
                    <input type="submit" value="修改" style="margin: 0 auto" onclick="return check()" class="submitBtn">
                    </tbody>
                </table>
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
