<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%><!-- ctrl+space해서 찾기//라이브러리가 있어야 인식함 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title"/></title><!-- 페이지마다 타이틀이 바뀌게. 문자열로 타이틀 읽어옴 -->
</head>
<body>

<!-- 상단 메뉴 -->
<tiles:insertAttribute name="header"/><!-- 헤더 계속 바뀌게 -->
<!-- 상단 메뉴 끝 -->
 
<!-- 내용 시작 -->
<tiles:insertAttribute name="body"/><!-- 바디 내용 계속 바뀌게 -->
<!-- 내용 끝 -->
 
</body>
</html>
