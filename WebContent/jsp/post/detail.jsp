<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>FoodMaster</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<tr>
		<td>${post.no }</td>
		<c:forEach var="j" items="${post.photolist }">
			<td>${j.url }</td>
			<td>${j.regDate }</td>	
		</c:forEach>
		<td>${post.content }</td>
		<td>${post.regDate }</td>
		<td>${post.userId }</td>
	</tr>
	<br/>
	<input type="button" value="수정" onclick="location.href='updateForm.do?no=${post.no}'">
	<input type="button" value="삭제" onclick="location.href='delete.do?no=${post.no}'">
	<%@ include file="/jsp/include/bottom.jsp"%>
</body>
</html>