<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<script>
	alert('게시글을 저장하였습니다');
	location.href="<%=request.getContextPath()%>/post/list.do";
</script>