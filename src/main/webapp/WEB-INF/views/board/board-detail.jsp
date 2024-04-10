<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>게시글 상세</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<script>
    window.onload = function () {
        document.querySelector("#listReq").onclick = backFunc;
        document.querySelector("#updateReq").onclick = updateFunc;
        document.querySelector("#deleteReq").onclick = deleteFunc;
    }

    function backFunc() {
        location.href = "/board/list";
    }

    function updateFunc() {
        const id = '${boardList.id}';
        location.href = "/board/update?id=" + id;
    }

    function deleteFunc() {
        const id = '${boardList.id}';
        location.href = "/board/delete?id=" + id;
    }
</script>
<body style="margin: 20px;">
<h3 style="text-align: center;">글 작성하기</h3>
<table style="width: 95%;" class="table">
    <tr>
        <td>작성자</td>
        <td>${boardList.boardWriter}</td>
    </tr>
    <tr>
        <td>글제목</td>
        <td>${boardList.boardTitle}</td>
    </tr>
    <tr>
        <td>글내용</td>
        <td>${boardList.boardContents}</td>
    </tr>
    <tr>
        <td>조회수</td>
        <td>${boardList.boardHits}</td>
    </tr>
    <tr>
        <td>파일</td>
        <td>${boardList.boardFile}</td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: center;">
            <input type="button" value="목록" id="listReq" class="btn btn-secondary"/>
            <input type="button" value="수정하기" id="updateReq" class="btn btn-primary"/>
            <input type="button" value="삭제하기" id="deleteReq" class="btn btn-danger"/>
        </td>
    </tr>
</table>
</body>
</html>
