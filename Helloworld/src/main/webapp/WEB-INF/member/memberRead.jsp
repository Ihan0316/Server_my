<%--
  Created by IntelliJ IDEA.
  User: ihanjo
  Date: 24. 11. 27.
  Time: 오후 3:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>mno 번호로 한명 조회하는 화면</h1>
<div>
    <input type="text" name="mno" value="${dto.mno}" readonly>
</div>
<div>
    <input type="text" name="title" value="${dto.name}" placeholder="이름 입력 해주세요." readonly>
</div>
<div>
    <input type="date" name="dueDate" value="${dto.dueDate}">
</div>
<div>
    <input type="checkbox" name="finished" ${dto.finished ? "checked" : ""} readonly>
</div>
<div>
    <a href="/member/update?mno=${dto.mno}">수정/삭제</a>
    <a href="/member/list">목록가기</a>
</div>
</body>
</html>
