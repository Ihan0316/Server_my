<%--
  Created by IntelliJ IDEA.
  User: ihanjo
  Date: 24. 11. 21.
  Time: 오후 4:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Read2</title>
</head>
<body>
<h1>bno 번호로 하나 조회하는 화면, 상세보기와 같음</h1>
<div>
    <input type="text" name="bno" value="${dto.bno}" readonly>
</div>
<div>
    <input type="text" name="title" placeholder="제목 입력해주세요" value="${dto.title}" readonly>
</div>
<div>
    <input type="date" name="dueDate" value="${dto.dueDate}" readonly>
</div>
<div>
    <input type="checkbox" name="finished" ${dto.finished ? "checked" :""} readonly>
</div>
<div>
    <a href="/book/update?tno=${dto.bno}">수정</a>
    <a href="/book/list2">목록가기</a>
</div>
</body>
</html>
