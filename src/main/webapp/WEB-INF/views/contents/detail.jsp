<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>상세페이지</title>
<meta charset="utf-8">

<script type="text/javascript">
	function updateM() {
		var url = "update";
		url += "?contentsno=${dto.contentsno}";
		location.href = url;
	}
	function deleteM() {
		var url = "delete";
		url += "?contentsno=${dto.contentsno}";
		location.href = url;
	}

	function listM() {
		var url = "list";
		url += "?nowPage=${param.nowPage}";
		url += "&col=${param.col}";
		url += "&word=${param.word}";
		location.href = url;
	}
</script>

</head>
<body>
	<div class="container">

		<h2>조회</h2>
		<div class="panel panel-default">
			<div class="panel-heading">상품번호</div>
			<div class="panel-body">${dto.contentsno}</div>
			
			<div class="panel-heading">분류번호</div>
			<div class="panel-body">${dto.cateno}</div>
			
			<div class="panel-heading">상품명</div>
			<div class="panel-body">${dto.pname}</div>

			<div class="panel-heading">가격</div>
			<div class="panel-body">${dto.price}</div>

			<div class="panel-heading">상세내용</div>
			<div class="panel-body">${dto.detail}</div>

			<div class="panel-heading">등록일</div>
			<div class="panel-body">${dto.rdate}</div>

		</div>
		<div>
			<button type="button" class="btn" onclick="location.href='./create'">등록</button>
			<button type="button" class="btn" onclick="updateM()">수정</button>
			<button type="button" class="btn" onclick="deleteM()">삭제</button>
			<button type="button" class="btn" onclick="listM()">목록</button>
		</div>
	</div>
</body>
</html>