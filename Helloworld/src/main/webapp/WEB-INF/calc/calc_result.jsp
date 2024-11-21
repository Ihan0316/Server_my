<%--
  Created by IntelliJ IDEA.
  User: ihanjo
  Date: 24. 11. 20.
  Time: 오후 2:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
<%--${} 표기는 서버에서 넘어온 데이터 조회하는 EL 표기법--%>
<%--다른 프론트 엔진인 타임리프 사용할 경우도 형식 많이 사용--%>
    <h1>넘어온 데이터 확인 num1 : ${param.num1}</h1>
    <h1>넘어온 데이터 확인 num2 : ${param.num2}</h1>
    <h1>넘어온 데이터 확인 num1 + num2 : ${Integer.parseInt(param.num1) + Integer.parseInt(param.num2)}</h1>
</body>
</html>
