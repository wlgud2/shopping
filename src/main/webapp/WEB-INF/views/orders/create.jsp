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

	function checkIn(f) {
		if (f.quantity.value == "") {
			alert("갯수를 입력하세요");
			f.quantity.focus()
			return false;
		}
		if (f.payment.value == "") {
			alert("결제수단을 입력하세요");
			f.payment.focus();
			return false;
		}
	}
</script>
</head>
<body>

	<div class="container">
		<h1 class="col-sm-offset-2 col-sm-10">상품 구매</h1>
		<div>
			<img src="/pstorage/${dto.filename}" style="width: 30%;">
		</div>
		<form class="form-horizontal" action="/orders/create" method="post"
			enctype="multipart/form-data" onsubmit="return checkIn(this)">
			<div class="form-group">
				<label class="control-label col-sm-2" for="pname">상품명</label>
				<div class="col-sm-8">
					<input type="text" value="${dto.pname }" id="pname"
						class="form-control-plaintext">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="detail">상품 정보</label>
				<div class="col-sm-8">
					<input type="text" value="${member.detail}" id="detail"
						class="form-control-plaintext">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="price">가격</label>
				<div class="col-sm-8">
					<input type="text" value="${dto.price}" id="price"
						class="form-control-plaintext">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="quantity">갯수</label>
				<div class="col-sm-8">
					<input type="text" name="quantity" id="quantity"
						class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="total">결제금액</label>
				<div class="col-sm-8">
					<input type="text" name="total" id="total"
						class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="payment">결제수단</label>
				<div class="col-sm-8">
					<input type="text" name="payment" id="payment" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="mname">받는 사람</label>
				<div class="col-sm-8">
					<input type="text" value="${dto.mname}" id="mname"
						class="form-control-plaintext">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="address">주소</label>
				<div class="col-sm-8">
					<input type="text" value="${member.address1},${member.address2}"
						id="address" class="form-control-plaintext">
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
