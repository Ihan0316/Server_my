<%--
  Created by IntelliJ IDEA.
  User: ihanjo
  Date: 24. 11. 27.
  Time: 오후 3:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Member 목록화면. </h1>
<a href="/member/register">회원등록 이동</a>
<h2>Member 한명 조회 더미 </h2>
<a href="/member/read?mno=1">하나 조회</a>

<h2>JSTL 연습장</h2>
<h3>반복문, forEach 이용, var=변수명, items="데이터 목록" , 더 많이 사용함</h3>
<ul>
    <c:forEach var="dto" items="${list}">
        <li>
            <span>${dto.mno}</span>
            <span><a href="/member/read?mno=${dto.mno}">${dto.name}</a></span>
            <span>${dto.dueDate}</span>
            <span>${dto.finished? "완료": "미완료"}</span>
        </li>
    </c:forEach>
</ul>
<a href="Hello-servlet">메인화면</a>
</body>
</html>
