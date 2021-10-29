<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>상세페이지</title>
<meta charset="utf-8">
</head>
<body>
	<div class="container">

		<h2>조회</h2>
		<div class="panel panel-default">
		<img src="/pstorage/${dto.filename}" class="img-rounded" width="100px" height="100px">
			<div class="panel-heading">주문번호</div>
			<div class="panel-body">${dto.orderno}</div>

			<div class="panel-heading">상품번호</div>
			<div class="panel-body">${dto.contentsno}</div>

			<div class="panel-heading">상품명</div>
			<div class="panel-body">${dto.pname}</div>

			<div class="panel-heading">가격</div>
			<div class="panel-body">${dto.price}</div>

			<div class="panel-heading">갯수</div>
			<div class="panel-body">${dto.quantity}</div>

			<div class="panel-heading">결제 금액</div>
			<div class="panel-body">${dto.total}</div>

			<div class="panel-heading">결제 수단</div>
			<div class="panel-body">${dto.payment}</div>

			<div class="panel-heading">결제 날짜</div>
			<div class="panel-body">${dto.odate}</div>

		</div>
		<div>
			<button type="button" class="btn" onclick="location.href='../../member/mypage'">목록</button>
		</div>
	</div>
</body>
</html>