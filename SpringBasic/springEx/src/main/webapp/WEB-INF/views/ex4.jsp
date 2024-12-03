<%--
  Created by IntelliJ IDEA.
  User: ihanjo
  Date: 24. 12. 3.
  Time: 오전 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>ex4, 서버로 넘어온 더미 데이터 확인</h1>
<%--<h2>넘어온 데이터 확인 msg : ${msg}</h2>--%>
<h2>넘어온 데이터 확인 msg : <c:out value="${msg}"/></h2>
xss의 문제점이 있어서, 문자열에 자바스크립트 같은 코드가 오게되면, 원하지 않는 자바스크립트가 실행이 될 위험이 있어서, 출력할 때는 무조건 출력 변환하는 기능 이용
</body>
</html>
