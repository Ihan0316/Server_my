<%--
  Created by IntelliJ IDEA.
  User: ihanjo
  Date: 24. 11. 20.
  Time: 오후 2:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Input</title>
</head>
<body>
<%--action: 서버에 전달할 경로, method : 형식--%>
<%--쿼리 사용시 타입을 기본형 으로 변경한다.--%>
<%--<form action="calc_result.jsp" method="post">--%>
<form action="/calc/result" method="post">
    <label>
        <input type="number" name="num1">
    </label>
    <label>
        <input type="number" name="num2">
    </label>
    <button>전송</button>
</form>
</body>
</html>
