<%--
  Created by IntelliJ IDEA.
  User: ihanjo
  Date: 24. 11. 21.
  Time: 오후 3:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JDBCex-Todo Read</title>
</head>
<body>
    <h1>tno 번호로 하나 조회하는 화면, 상세보기와 같음</h1>
    <div>
        <input type="text" name="tno" value="${dto.tno}" readonly>
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
        <a href="/todo/update?tno=${dto.tno}">수정</a>
        <a href="/todo/list2">목록가기</a>
    </div>
</body>
</html>
