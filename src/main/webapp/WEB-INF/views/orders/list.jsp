<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="util" uri="/ELFunctions" %>

 
<!DOCTYPE html> 
<html> 
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
</head>
<body>
<div class="container">
 
  <h2>구매 목록</h2>
  <form class="form-inline" action="./list">
    <div class="form-group">
      <select class="form-control" name="col">
        <option value="cateno"
        <c:if test= "${col=='cateno'}"> selected </c:if>
        >상품분류(Jean:1,Bag:2)</option>
        <option value="pname"
        <c:if test= "${col=='pname'}"> selected </c:if>
        >상품명</option>
        <option value="price"
        <c:if test= "${col=='price'}"> selected </c:if>
        >가격</option>
        <option value="total"
        <c:if test= "${col=='total'}"> selected </c:if>
        >전체출력</option>       
     </select>
    </div>
    <div class="form-group">
      <input type="text" class="form-control" placeholder="Enter 검색어" 
      name="word" value="${word}">
    </div>
    <button type="submit" class="btn btn-default" >검색</button>
  </form>
  
  <table class="table table-striped">
   <thead>
    <tr>
	<th>주문번호</th>
    <th>상품명</th>
    <th>상품이미지</th>
    <th>갯수</th>
    <th>가격</th>
    <th>구매날짜</th>
    </tr>
   </thead>
   <tbody>
<tr>
<c:choose>   
<c:when test="${empty dto.list}">
   <tr><td colspan="6">구매한 상품이 없습니다.</td>
</c:when>
<c:otherwise>
   <c:forEach var="order" items="${dto.list}"> 
	<li class="list-group-item">
	<a href="/orders/detail/${order.orderno }">${order.orderno}</a>
	/<a href="/contents/detail/${dto.contentsno }">${dto.pname}</a>
	/<img src="/pstorage/${dto.filename}" class="img-rounded" width="50px" height="50px">
	/${order.quantity}/${order.total }원/${fn:substring(order.odate,0,10)}
	<a href="review/create/{order.contentsno}"><span class="badge">Review</span></a></li>
   </c:forEach>
</c:otherwise>
</c:choose>
</tr>
</tbody>
</table>
  <div>
      ${paging}
  </div>
</div>
</body> 
</html> 
