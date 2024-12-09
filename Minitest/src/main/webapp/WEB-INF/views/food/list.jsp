<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
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
                        <a class="navbar-brand" href="/food/list">목록가기</a>
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

        <%--    검색 및 필터 화면 추가--%>
        <div class="row content">
            <div class="col">
                <!--        카드 시작 부분-->
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">검색 및 필터 </h5>
                        <%--                        검색 입력창, 검색 조건, 필터 조건                        --%>
                        <form action="/food/list" method="get">
                            <input type="hidden" name="size" value="${pageRequestDTO.size}">
                            <div class="mb-3">
                                <input type="checkbox" name="finished" ${pageRequestDTO.finished ?"checked":""}> 완료여부
                            </div>
                            <div class="mb-3">
                                <input type="checkbox" name="types" value="t" ${pageRequestDTO.checkType("t")?"checked":""}>음식
                                <input type="checkbox" name="types" value="w" ${pageRequestDTO.checkType("w")?"checked":""}>요리사
                                <input type="text" name="keyword" class="form-control" value="${pageRequestDTO.keyword}">
                            </div>
                            <div class="input-group mb-3 dueDateDiv">
                                <input type="date" name="from" class="form-control" value="${pageRequestDTO.from}">
                                <input type="date" name="to" class="form-control" value="${pageRequestDTO.to}">
                            </div>
                            <div class="input-group mb-3">
                                <div class="float-end">
                                    <button class="btn btn-primary" type="submit">검색</button>
                                    <button class="btn btn-secondary clearBtn" type="reset">초기화</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <!--        카드 끝 부분-->

            </div>

        </div>
        <%--    검색 및 필터 화면 추가--%>

        <!--        class="row content"-->
        <div class="row content">
            <!--        col-->
            <div class="col">
                <!--        카드 시작 부분-->
                <div class="card">
                    <div class="card-body">
                        <%--                        Food List 부분 작성--%>
                        <h5 class="card-title">리스트 목록</h5>
                        <button type="button" class="btn btn-primary insertFoodBtn">글쓰기</button>
                        <table class="table">
                            <thead>
                            <%--                                소제목--%>
                            <tr>
                                <th scope="col">Fno</th>
                                <th scope="col">FoodName</th>
                                <th scope="col">ChefName</th>
                                <th scope="col">DueDate</th>
                                <th scope="col">Finished</th>
                            </tr>
                            </thead>
                            <%--                                본문--%>
                            <tbody>

                            <c:forEach items="${pageResponseDTO.dtoList}" var="dto">
                                <tr>
                                    <th scope="row"><c:out value="${dto.fno}"></c:out></th>
                                    <td><a href="/food/read?fno=${dto.fno}&${pageRequestDTO.link}"
                                           class="text-decoration-none">
                                        <c:out value="${dto.foodName}"></c:out>
                                    </a></td>
                                    <td><c:out value="${dto.chefName}"></c:out></td>
                                    <td><c:out value="${dto.dueDate}"></c:out></td>
                                    <td><c:out value="${dto.finished}"></c:out></td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                        <%--                       Food List 부분 작성--%>
                        <div class="float-end">
                            <ul class="pagination">

                                <c:if test="${pageResponseDTO.prev}">
                                    <li class="page-item">
                                        <a class="page-link" data-num="${pageResponseDTO.page - 1}">Previous</a>
                                    </li>
                                </c:if>
                                <c:forEach begin="${pageResponseDTO.start}"
                                           end="${pageResponseDTO.end}" var="num">

                                    <li class="page-item ${pageResponseDTO.page == num ? "active" : ""}"
                                    ><a class="page-link" data-num="${num}" href="#">${num}</a></li>
                                </c:forEach>

                                <c:if test="${pageResponseDTO.next}">
                                    <li class="page-item">
                                        <a class="page-link" data-num="${pageResponseDTO.end + 1}">Next</a>
                                    </li>
                                </c:if>
                            </ul>
                        </div>

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

<script>
    const serverValidResult = {};

    <c:forEach items="${errors}" var="error">
    serverValidResult['${error.getField()}'] = '${error.defaultMessage}'
    </c:forEach>
    console.log(serverValidResult)


    const serverValidResult2 = {};

    <c:forEach items="${errors2}" var="error">
    serverValidResult2['${error.getField()}'] = '${error.defaultMessage}'
    </c:forEach>
    console.log(serverValidResult2)
</script>

<script>
    document.querySelector(".insertFoodBtn").addEventListener("click",
        function (e) {
// 글쓰기 폼으로 가야함.
            self.location = "/food/register"
                , false
        })


    document.querySelector(".pagination").addEventListener("click",
        function (e) {
            e.preventDefault()
            e.stopPropagation()
            const target = e.target

            if (target.tagName !== "A") {
                return
            }
            const num = target.getAttribute("data-num")

            const formObj = document.querySelector("form")
            // 자바스크립트에서, 백틱 안에서, 문자열 구현하기가 쉽다.
            formObj.innerHTML += `<input type='hidden' name='page' value='\${num}'>`
            formObj.submit()


        }, false)

    document.querySelector(".clearBtn").addEventListener("click",
        function (e){
        e.preventDefault();
        e.stopPropagation();

        self.location="/food/list"
    }, false)


</script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>