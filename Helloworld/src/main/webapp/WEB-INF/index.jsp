<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
        </h1>
<h2>index.jsp 기본 화면으로 접근 하는 방식을 , 서블릿을 통해서 접근으로 변경</h2>
        <br/>
        <a href="hello-servlet">Hello Servlet</a>
        <a href="/todo/list">todo-list</a>
        <a href="/todo/list2">todo-list2</a>
        <a href="/food/list">food-list</a>
        <button><a href="/login/input">로그인</a></button>
</body>
</html>