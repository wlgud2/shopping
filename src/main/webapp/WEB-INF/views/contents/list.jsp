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
 
  <h2>상품 목록</h2>
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
	<c:if test="${not empty sessionScope.id && sessionScope.grade == 'A'}">
    <button type="button" class="btn btn-default"
     onclick="location.href='../admin/create'">등록</button>
    </c:if>
  </form>
  
  <table class="table table-striped">
   <thead>
    <tr>
    <th>번호</th>
    <th>상품이미지</th>
    <th>상품명</th>
    <th>가격</th>
    <th>등록날짜</th>
    <th>재고</th>
    <c:choose>
    <c:when test="${not empty sessionScope.id && sessionScope.grade == 'A'}">
    	<th>수정/삭제/이미지수정</th>
    </c:when>
    <c:otherwise>
    	<th>장바구니/구매</th>
    </c:otherwise>
    </c:choose>
    </tr>
   </thead>
   <tbody>
 
<c:choose>   
<c:when test="${empty list}">
   <tr><td colspan="6">등록된 상품이 없습니다.</td>
</c:when>
<c:otherwise>
  
   <c:forEach var="dto" items="${list}"> 
   
   <tr>
    <td>${dto.contentsno}</td>
    <td>
    <img src="/pstorage/${dto.filename}" class="img-rounded" width="100px" height="100px">
    </td>
    <td>
    <a href="/contents/detail/${dto.contentsno }">${dto.pname}</a>
    <c:if test="${util:newImg(fn:substring(dto.rdate,0,10)) }">
         <img src="/images/new.gif"> 
    </c:if> 
    
    </td>
    <td>${dto.price}</td>
    <td>${dto.rdate}</td>
    <td>${dto.stock}</td>
	<c:choose>
    <c:when test="${not empty sessionScope.id && sessionScope.grade == 'A'}">
    	<td> <a href="../admin/update/${dto.contentsno }">
          &nbsp;&nbsp;<span class="glyphicon glyphicon-edit"></span>
       		 </a>
        /
        <a href="../admin/delete/${dto.contentsno }">
          <span class="glyphicon glyphicon-trash"></span>
        </a>
        /
        <a href="../admin/updateFile/${dto.contentsno }/${dto.filename}">
          <span class="glyphicon glyphicon-picture"></span>
        </a>     
    	</td>
    </c:when>
    <c:otherwise>
    	<td> <a href="../cart/create/${dto.contentsno }">
          &nbsp;&nbsp;&nbsp;&nbsp;
          <span class="glyphicon glyphicon-shopping-cart"></span>
       		 </a>
        /
        <a href="../orders/create/${dto.contentsno }">
          <span class="glyphicon glyphicon-usd"></span>
        </a>
    	</td>
    </c:otherwise>
    </c:choose>
    
   </tr>
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
