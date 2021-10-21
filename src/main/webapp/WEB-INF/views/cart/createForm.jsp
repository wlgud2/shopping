<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>homepage</title>
<meta charset="utf-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/ckeditor/ckeditor.js">
	
</script>
<script type="text/JavaScript">
	/**$(function() {
		CKEDITOR.replace('detail'); // <TEXTAREA>태그 id 값
	});*/
</script>
</head>
<body>

	<div class="container">
		<h1 class="col-sm-offset-2 col-sm-10">장바구니 담기</h1>
		<form class="form-horizontal" action="/orders/create" method="post"
			enctype="multipart/form-data" onsubmit="return checkIn(this)">

			<div class="form-group">
				<label class="control-label col-sm-2" for="pname">상품명</label>
				<div class="col-sm-8">
					<input type="text" value="${pname }" id="pname"
						class="form-control-plaintext">
				</div>
			</div>
			<img src="/pstorage/${dto.filename}" class="img-rounded" width="50px"
				height="50px">
			<div class="form-group">
				<label class="control-label col-sm-2" for="price">가격</label>
				<div class="col-sm-8">
					<input type="text" value="${price}" id="price"
						class="form-control-plaintext">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="detail">상품 정보</label>
				<div class="col-sm-8">
					<input type="text" value="${detail}" id="detail"
						class="form-control-plaintext">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-5">
					<button class="btn">장바구니 담기</button>
					<button type="reset" class="btn" onclick="location.href='./list'">취소</button>
				</div>
			</div>

		</form>
	</div>
</body>
</html>
