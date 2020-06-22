<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
isErrorPage="true"%>
<html>
  <head>
    <title>业务层异常页面</title>
  </head>
  <body>
  	<%=((Exception)request.getAttribute("se")).getMessage() %> <br>
  </body>
</html>
