<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Food update</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!--        <h1>Header</h1>-->
        <!--        네비게이션바 추가 시작-->
        <div class="row">
            <div class="col">
                <nav class="navbar navbar-expand-lg bg-body-tertiary">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#">Navbar</a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                                aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNav">
                            <ul class="navbar-nav">
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Features</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Pricing</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>

            </div>
        </div>
        <!--        네비게이션바 추가 끝-->

        <!--        class="row content"-->
        <div class="row content">
            <!--        col-->
            <div class="col">
                <!--        카드 시작 부분-->
                <div class="card">
                    <div class="card-header">
                        Featured
                    </div>
                    <div class="card-body">
                        <%--                        Food 입력 폼 여기에 작성--%>
                        <form action="/food/update" method="post">
                            <input type="hidden" name="page" value="${pageRequestDTO.page}">
                            <input type="hidden" name="size" value="${pageRequestDTO.size}">
                            <div class="input-group mb-3">
                                <span class="input-group-text">Fno</span>
                                <input type="text" name="fno" class="form-control" readonly
                                       value=<c:out value="${foodDTO.fno}"/> >
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">FoodName</span>
                                <input type="text" name="foodName" class="form-control" placeholder="음식을 입력하세요"
                                       value='<c:out value="${foodDTO.foodName}"/>'>
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text">DueDate</span>
                                <input type="date" name="dueDate" class="form-control"
                                       value=<c:out value="${foodDTO.dueDate}"/>>
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text">ChefName</span>
                                <input type="text" name="chefName" class="form-control" readonly
                                       value=<c:out value="${foodDTO.chefName}"/>>
                            </div>
                            <div class="input-group mb-3">
                                <label class="form-check-label">Finished &nbsp</label>
                                <input type="checkbox" name="finished" class="form-check-input"
                                       ${foodDTO.finished ? "checked" : ""}>
                            </div>
                            <div class="my-4">
                                <div class="float-end">
                                    <button type="submit" class="btn btn-primary">적용하기</button>
                                    <button type="button" class="btn btn-danger">삭제하기</button>
                                    <button type="button" class="btn btn-secondary">목록가기</button>
                                </div>
                            </div>
                        </form>
                        <%--                        Food 입력 폼 여기에 작성--%>

                    </div>
                </div>
                <!--        카드 끝 부분-->
            </div>
            <!--        col-->
        </div>
        <!--        class="row content"-->
    </div>
<%--    <div class="row content">--%>
<%--        <h1>Content</h1>--%>
<%--    </div>--%>
    <div class="row footer">
        <!--        <h1>Footer</h1>-->
        <div class="row fixed-bottom" style="z-index: -100">
            <footer class="py-1 my-1">
                <p class="text-center text-muted">Footer</p>
            </footer>
        </div>
    </div>
</div>
<%--입력 폼에 관련 유효성 체크, 서버로부터  erros 키로 값을 받아오면, --%>
<%--자바스크립 콘솔에 임시 출력.--%>
<script>
    const serverValidResult = {    };
    // jstl , 반복문으로, 서버로부터 넘어온 여러 에러 종류가 많습니다.
    //     하나씩 꺼내서, 출력하는 용도.,
    <c:forEach items="${errors}" var="error">
    serverValidResult['${error.getField()}'] = '${error.defaultMessage}'
    </c:forEach>
    console.log(serverValidResult)
</script>

<%--목록가기 및 수정폼 가기 이벤트 리스너--%>
<script>
    // 수정폼
    document.querySelector(".btn-primary").addEventListener("click",
        function (e) {
            self.location = "/food/update?fno=" +${foodDTO.fno}
                , false
        })
    // 목록
    document.querySelector(".btn-secondary").addEventListener("click",
        function (e) {
            self.location = "/food/list?${pageRequestDTO.link}"
                , false
        })

    // 삭제기능.
    document.querySelector(".btn-danger").addEventListener("click",
        function (e) {
            const formObj = document.querySelector("form")

            e.preventDefault()
            e.stopPropagation() // 상위 태그로 전파 방지


            formObj.action = "/food/delete?${pageRequestDTO.link}"
            formObj.method = "post"
            formObj.submit()
        }, false)

    // 방법2
    //수정 로직 처리
    document.querySelector(".btn-primary").addEventListener("click",
        function (e) {
            const formObj = document.querySelector("form")

            e.preventDefault()
            e.stopPropagation() // 상위 태그로 전파 방지

            formObj.action = "/food/update?${pageRequestDTO.link}"
            formObj.method = "post"
            formObj.submit()
        }, false)
</script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>