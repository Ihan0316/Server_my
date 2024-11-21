<%--
  Created by IntelliJ IDEA.
  User: ihanjo
  Date: 24. 11. 21.
  Time: 오전 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo List</title>
</head>
<body>
  <h1>Todo List 목록화면</h1>
    <a href="/todo/register">글쓰기 폼 이동</a>
<h2>더미 데이터 단순 출력</h2>
  <h3>list 중에서 list[0]</h3>
    ${list[0]}
  <h3>list 중에서 list[0].tno</h3>
    ${list[0].tno}
  <h3>list 중에서 list[0].title</h3>
    ${list[0].title}
  <h3>list 전체 호출</h3>
    ${list}
</body>
</html>
