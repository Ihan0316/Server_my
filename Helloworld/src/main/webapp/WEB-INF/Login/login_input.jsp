<%--
  Created by IntelliJ IDEA.
  User: ihanjo
  Date: 24. 11. 20.
  Time: 오후 2:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login_input</title>
</head>
<body>
<%--<form method="POST" action="login_result2.jsp">--%>
<form method="POST" action="/login/result">
    <h4>LOGIN 화면 접근을 서블릿으로 해보기</h4>
    <label>
        <input type="text" name="username" placeholder="Username"/>
    </label>
    <label>
        <input type="password" name="password" placeholder="Password"/>
    </label>
    <input type="submit" value="Login"/>
</form>
</body>
</html>
