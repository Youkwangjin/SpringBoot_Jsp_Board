<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>게시판 글쓰기</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $(document).ready(function (){
        $('#boardForm').on('submit', function (e) {
            e.preventDefault();
            let formData = new FormData(this);
            $.ajax({
                url: '/board/save',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function () {
                    alert("게시글이 등록되었습니다.");
                    window.location.href = '/board/list';
                },
                error: function () {
                    alert("서버에 심각한 오류가 발생했습니다. 다시 시도해주세요");
                }
            })
        });
    });
</script>
<body style="margin: 20px;">
<h3 style="text-align: center;">글 작성하기</h3>
<form id="boardForm" method="post" enctype="multipart/form-data">
    <table style="width: 95%;" class="table">
        <tr>
            <td>작성자</td>
            <td>
                <label>
                    <input type="text" name="boardWriter"/>
                </label>
            </td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td>
                <label>
                    <input type="text" name="boardPassword"/>
                </label>
            </td>
        </tr>
        <tr>
            <td>글제목</td>
            <td>
                <label>
                    <input type="text" name="boardTitle"/>
                </label>
            </td>
        </tr>
        <tr>
            <td>글내용</td>
            <td>
                <label>
                    <textarea rows="5" style="width: 99%;" name="boardContents"></textarea>
                </label>
            </td>
        </tr>
        <tr>
            <td>파일 첨부하기</td>
            <td>
                <label>
                    <input type="file" name="boardFile" multiple/>
                </label>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" value="작성하기" class="btn btn-primary"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
