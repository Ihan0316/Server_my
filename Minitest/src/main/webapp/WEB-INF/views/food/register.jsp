<%--
  Created by IntelliJ IDEA.
  User: ihanjo
  Date: 24. 12. 2.
  Time: 오후 3:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>/food/register, register.jsp 화면입니다.</h1>
<form action="/food/register" method="post">
    <div>
        FoodName : <input type="text" name="foodName">
    </div>
    <div>
        DueDate : <input type="date" name="dueDate" value="2024-12-03">
    </div>
    <div>
        ChefName : <input type="text" name="chefName">
    </div>
    <div>
        Finished : <input type="checkbox" name="finished">
    </div>
    <button type="submit">글작성</button>
</form>
</body>
</html>
