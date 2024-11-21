<%--
  Created by IntelliJ IDEA.
  User: ihanjo
  Date: 24. 11. 21.
  Time: 오전 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
// JSTL 사용 준비단계
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

  <h2>JSTL 연습장</h2>
<h3>반복문 forEach 이용, var = 변수명, items = "데이터 목록"</h3>
<ul>
  <c:forEach var="dto" items="${items}">
    <li>${dto}</li>
  </c:forEach>
</ul>

</body>
</html>
