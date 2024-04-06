<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>게시판 목록</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3 style="text-align: center;">게시판</h3>
            <a href="/board/write" class="btn btn-primary">게시글 작성하기</a><br/><br/>
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>게시글 번호</th>
                    <th>게시글 제목</th>
                    <th>게시글 작성자</th>
                    <th>게시글 작성일</th>
                    <th>게시글 조회수</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="board" items="${boardList}">
                    <tr>
                        <td>${board.id}</td>
                        <td><a href="/board/detail/${board.id}">${board.boardTitle}</a></td>
                        <td>${board.boardWriter}</td>
                        <td>${board.boardCreated}</td>
                        <td>${board.boardHits}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!-- 페이지네이션 부분 시작 -->
            <!-- 페이지네이션 부분 끝 -->
        </div>
    </div>
</div>
</body>
</html>
