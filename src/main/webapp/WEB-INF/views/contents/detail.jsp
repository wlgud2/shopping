<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>상세페이지</title>
<meta charset="utf-8" />

<script type="text/javascript">
	function listM() {
		var url = "../list";
		url += "?nowPage=${param.nowPage}";
		url += "&col=${param.col}";
		url += "&word=${param.word}";
		location.href = url;
	}
</script>
</head>
<body>
	<div class="container">
		<h2>상세정보</h2>
		<div style="width: 50%; float: left;">
			<img src="/pstorage/${dto.filename}" style="width: 100%;">
			<div style="float: right; margin-right: 3px;">
				<c:choose>
					<c:when
						test="${not empty sessionScope.id && sessionScope.grade == 'A'}">
						<button type="button" class="btn"
							onclick="location.href='/admin/create'">등록</button>
						<button type="button" class="btn"
							onclick="location.href='/admin/update/${contentsno}'">
							수정</button>
						<button type="button" class="btn"
							onclick="location.href='/admin/delete/${contentsno}'">
							삭제</button>
					</c:when>
					<c:otherwise>
						<div class="form-group">
							<label class="control-label col-sm-3" for="quantity">갯수</label>
							<div class="col-sm-3">
								<input type="text" name="quantity" id="quantity"
									class="form-control">
							</div>
						</div>
						<br><button type="button" class="btn"
							onclick="location.href='../../cart/create/${contentsno}'">
							장바구니</button>
						<button type="button" class="btn"
							onclick="location.href='../../orders/create/${contentsno}'">
							구매하기</button>
					</c:otherwise>
				</c:choose>
				<button type="button" class="btn" onclick="history.back()">목록</button>
			</div>
		</div>
		<div class="panel panel-default" style="width: 50%; float: left;">
			<div class="panel-heading">상품번호</div>
			<div class="panel-body">${dto.contentsno}</div>

			<div class="panel-heading">분류번호</div>
			<div class="panel-body">${dto.cateno}</div>

			<div class="panel-heading">상품명</div>
			<div class="panel-body">${dto.pname}</div>

			<div class="panel-heading">가격</div>
			<div class="panel-body">${dto.price}</div>

			<div class="panel-heading">재고</div>
			<div class="panel-body">${dto.stock}</div>

			<div class="panel-heading">상세내용</div>
			<div class="panel-body">${dto.detail}</div>

			<div class="panel-heading">등록일</div>
			<div class="panel-body">${dto.rdate}</div>
		</div>

	</div>
</body>
</html>
