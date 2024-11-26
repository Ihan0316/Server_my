<%--
  Created by IntelliJ IDEA.
  User: ihanjo
  Date: 24. 11. 21.
  Time: 오전 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--jstl--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Book List</title>
</head>
<body>
<h1>Book List 화면</h1>
<p>책 .....</p>
<a href="/book/bookregister">책 추가 이동</a>

<h2>Book List 하나 조회 더미</h2>
<a href="/book/read2?bno=5">하나 조회</a>

<h2>JSTL 연습장</h2>
<h3>반복문, forEach 이용, var=변수명, items="데이터 목록" , 더 많이 사용함</h3>
<ul>
    <c:forEach var="dto" items="${list}">
        <li>${dto}</li>
    </c:forEach>
</ul>
</body>
</html>
