<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0; background-image : url('/FmProject/resources/images/top.jpg');">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand huge" href="/FmProject/multiList.do">Food-Master</a>
	</div>
	<!-- /.navbar-header -->
	<div class="text-right">
		<c:if test="${userVO!=null}">
		<h4><strong class="text-info">${userVO.name}님 환영합니다.</strong></h4>
		</c:if>
	</div>
