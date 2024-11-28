<%--
  Created by IntelliJ IDEA.
  User: ihanjo
  Date: 24. 11. 27.
  Time: 오후 3:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--JSTL 추가--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Login</h1>

<%--만약 로그인시, 예외 발생하면, 서버에서 키 : result, 값 : error 넘어옴, 쿼리스트링으로--%>
<%--화면에서 jstl라이브러리 이용하여 error넘어오면 로그인 잘못된 내용 표기하기--%>

<c:if test="${param.result == 'error'}">
    <h1>로그인 정보 다시 확인 후 로그인 해주세요</h1>
</c:if>
<form action="/login" method="post">
  <input type="text" name="mid">
  <input type="text" name="mpw">
  <button type="submit">로그인</button>
</form>
</body>
</html>
