<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <form action="${pageContext.request.contextPath}/mypage" method="post" class="mt-5">
                <h2 class="text-center mb-4">로그인</h2>
                <div class="form-group">
                    <label for="memberEmail">이메일</label>
                    <input type="text" class="form-control" id="memberEmail" name="memberEmail" placeholder="이메일">
                </div>
                <div class="form-group">
                    <label for="memberPassword">비밀번호</label>
                    <input type="password" class="form-control" id="memberPassword" name="memberPassword" placeholder="비밀번호">
                </div>
                <button type="submit" class="btn btn-primary btn-block">로그인</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
