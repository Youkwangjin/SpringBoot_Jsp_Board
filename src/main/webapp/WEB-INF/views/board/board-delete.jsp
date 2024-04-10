<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>게시판 상세</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        $('boardDeleteForm').on('submit', function (e) {
            e.preventDefault();

            const boardPassword = "${boardList.boardPassword}";
            const inputPassword = $('input[name="boardPassword"]').val();

            const id = "${boardList.id}";

            if (inputPassword === boardPassword) {
                let deleteFormData = new FormData(this);
                $.ajax({
                    url: "/board/delete?id=" + id,
                    type: 'POST',
                    data: deleteFormData,
                    processData: false,
                    contentType: false,
                    success: function () {
                        alert("게시글이 삭제되었습니다.");
                        window.location.href = '/board/list';
                    },
                    error: function () {
                        alert("서버에 심각한 오류가 발생했습니다.");
                    }
                })
            } else {
                alert("비밀번호가 일치하지 않습니다.")
            }
        })
    })

</script>
<body style="margin: 20px;">
<h3 style="text-align: center;">글 작성하기</h3>
<form id="boardDeleteForm" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${boardList.id}" readonly>
    <table style="width: 95%;" class="table">
        <tr>
            <td>작성자</td>
            <td>${boardList.boardWriter}</td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td>
                <label>
                    <input type="password" name="boardPassword"/>
                </label>
            </td>
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
            <td>파일 첨부하기</td>
            <td>${boardList.boardFile}</td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" value="삭제하기" class="btn btn-danger"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
