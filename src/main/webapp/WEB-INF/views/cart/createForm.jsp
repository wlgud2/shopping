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
	$(function() {
		CKEDITOR.replace('detail'); // <TEXTAREA>태그 id 값
	});

	function checkIn(f) {
		if (f.pname.value == "") {
			alert("상품명을 입력하세요");
			f.pname.focus()
			return false;
		}
		if (f.price.value == "") {
			alert("가격을 입력하세요");
			f.price.focus();
			return false;
		}
		if (CKEDITOR.instances['detail'].getData() == '') {
			window.alert('내용을 입력해 주세요.');
			CKEDITOR.instances['detail'].focus();
			return false;
		}
		if (f.stock.value == "") {
			alert("재고를 입력하세요");
			f.stock.focus();
			return false;
		}
		if (f.filename.value == "") {
			alert("상품이미지를 선택하세요");
			f.filename.focus();
			return false;
		}
	}
</script>
</head>
<body>

	<div class="container">
		<h1 class="col-sm-offset-2 col-sm-10">상품 구매</h1>
		<form class="form-horizontal" action="/orders/create" method="post"
			enctype="multipart/form-data" onsubmit="return checkIn(this)">
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="pname">상품명: ${pname }</label>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="price">가격: ${price*quantity}</label>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="detail">상품 설명: ${detail }</label>
				<div class="col-sm-8">
					<textarea rows="12" cols="7" id="detail" name="detail"
						class="form-control"></textarea>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="filenameMF">상품이미지</label>
				<div class="col-sm-6">
					<input type="file" name="filenameMF" id="filenameMF"
						class="form-control">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-5">
					<button class="btn">구매</button>
					<button type="reset" class="btn" onclick="location.href='./list'">취소</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>