<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
isErrorPage="true"%>
<html>
  <head>
    <title>数据操作层异常页面</title>
  </head>
  <body>
    <%=((Exception)request.getAttribute("de")).getMessage() %> <br>
  </body>
</html>
