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
 
  <h2>장바구니 목록</h2>
  <table class="table table-striped">
   <thead>
    <tr>
    <th>번호</th>
    <th>상품명</th>
    <th>상품이미지</th>
    <th>가격</th>
    <th>재고</th>
    <th>구매</th>
    </tr>
   </thead>
   <tbody>

<c:choose>   
<c:when test="${empty list}">
   <tr><td colspan="6">등록된 상품이 없습니다.</td>
</c:when>
<c:otherwise>
  
   <c:forEach var="cart" items="${list}"> 
	<li class="list-group-item">
	${cartno}
	/<a href="/contents/detail/${dto.contentsno }">${dto.pname}</a>
	/<img src="/pstorage/${dto.filename}" class="img-rounded" width="50px" height="50px">
    <td>${dto.price}</td>
    <td>${dto.stock}</td>
        <a href="../order/create/${dto.contentsno }">
          <span class="glyphicon glyphicon-usd"></span>
        </a>
        </li>
        </c:forEach>
   </c:otherwise>
   </c:choose>
 
   </tbody>
  
  </table>
  <div>
      ${paging}
  </div>
</div>
</body> 
</html> 
