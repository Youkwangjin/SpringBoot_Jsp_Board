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
            <a href="${pageContext.request.contextPath}/board/write" class="btn btn-primary">게시글 작성하기</a><br/><br/>
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
                        <td>
                            <a href="${pageContext.request.contextPath}/board/detail?id=${board.id}">${board.boardTitle}</a>
                        </td>
                        <td>${board.boardWriter}</td>
                        <td>${board.boardCreated}</td>
                        <td>${board.boardHits}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!-- 페이지 -->
            <nav aria-label="Page navigation" style="display: flex; justify-content: center;">
                <ul class="pagination">
                    <c:if test="${boardPageDTO.startPage > 1}">
                        <li><a href="${pageContext.request.contextPath}/board/list/paging?page=${boardPageDTO.startPage - 1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                    </c:if>
                    <c:forEach begin="${boardPageDTO.startPage}" end="${boardPageDTO.endPage}" var="pageNum">
                        <li class="${pageNum == boardPageDTO.page ? 'active' : ''}">
                            <a href="${pageContext.request.contextPath}/board/list/paging?page=${pageNum}">${pageNum}</a>
                        </li>
                    </c:forEach>
                    <c:if test="${boardPageDTO.endPage < boardPageDTO.maxPage}">
                        <li><a href="${pageContext.request.contextPath}/board/list/paging?page=${boardPageDTO.endPage + 1}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
</html>
