<%--
  Created by IntelliJ IDEA.
  User: ihanjo
  Date: 24. 11. 21.
  Time: 오전 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book List</title>
</head>
<body>
<h1>Book List 화면</h1>
<p>책 .....</p>
<a href="/book/bookregister">책 추가 이동</a>

<h2>Book List 하나 조회 더미</h2>
<a href="/book/read?fno=5">하나 조회</a>

<h2>더미 데이터 단순 출력</h2>
<h3>list 중에서 list[0]</h3>
${list[0]}
<h3>list 중에서 list[0].fno</h3>
${list[0].fno}
<h3>list 중에서 list[0].title</h3>
${list[0].title}
<h3>list 전체 호출</h3>
${list}
</body>
</html>
