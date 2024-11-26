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
    <title>BookList Reg2</title>
</head>
<body>
<h1>Book의 책추가 폼 화면</h1>
<form action= "/book/register2" method="post">
    <div>
        <input type="text" name="title" placeholder="제목 입력해주세요">
    </div>
    <div>
        <input type="date" name="dueDate">
    </div>
    <button type="reset">초기화</button>
    <button type="submit">등록</button>
</form>
<a href="/">메인화면</a>
</body>
</html>
