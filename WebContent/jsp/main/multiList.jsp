<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>FoodMaster</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap Core CSS -->
<link href="/FmProject/resources/startbootstrap/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- MetisMenu CSS -->
<link href="/FmProject/resources/startbootstrap/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="/FmProject/resources/startbootstrap/dist/css/sb-admin-2.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="/FmProject/resources/startbootstrap/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<!-- jQuery -->
<script src="/FmProject/resources/startbootstrap/vendor/jquery/jquery.min.js"></script>
<script>
	$(function(){
		var totalSize = ${totalSize};
		var riceSize = (('${riceSize}'/'${totalSize}')*100)+'%';
		var noodleSize = (('${noodleSize}'/'${totalSize}')*100)+'%';
		var meatSize = (('${meatSize}'/'${totalSize}')*100)+'%';
		var soupSize = (('${soupSize}'/'${totalSize}')*100)+'%';
		
		$('.progress-bar.progress-bar-success').css('width',riceSize);
		$('.progress-bar.progress-bar-info').css('width',noodleSize);
		$('.progress-bar.progress-bar-warning').css('width',meatSize);
		$('.progress-bar.progress-bar-danger').css('width',soupSize);
	});
</script>
</head>
<body>
	<div id="wrapper" style="background-image : url('/FmProject/resources/images/side.jpg');">
		<%@ include file="/jsp/include/topBar.jsp"%>
		<%@ include file="/jsp/include/sideBar.jsp"%>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<ul class="nav navbar-top-links navbar-right">
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#"> <i class="fa fa-sliders fa-4x"></i>
						</a>
							<ul class="dropdown-menu dropdown-tasks">
							<li class="divider"></li>
								<li><a href="/FmProject/multiList.do">
										<div>
											<p>
												<strong>전체보기</strong>
											</p>
											<div class="progress progress-striped active">
												<div class="progress-bar progress-bar-default"
													role="progressbar" aria-valuenow="100" aria-valuemin="0"
													aria-valuemax="100" style="width:100%">
												</div>
											</div>
										</div>
								</a></li>
								<li><a href="/FmProject/multiList.do?menu=21">
										<div>
											<p>
												<strong>밥류</strong>
											</p>
											<div class="progress progress-striped active">
												<div class="progress-bar progress-bar-success"
													role="progressbar" aria-valuenow=" ${riceSize}" aria-valuemin="0"
													aria-valuemax="${totalSize}">
												</div>
											</div>
										</div>
								</a></li>
								<li class="divider"></li>
								<li><a href="/FmProject/multiList.do?menu=22">
										<div>
											<p>
												<strong>면류</strong> 
											</p>
											<div class="progress progress-striped active">
												<div class="progress-bar progress-bar-info"
													role="progressbar" aria-valuenow="${noodleSize}" aria-valuemin="0"
													aria-valuemax="${totalSize}">
												</div>
											</div>
										</div>
								</a></li>
								<li class="divider"></li>
								<li><a href="/FmProject/multiList.do?menu=23">
										<div>
											<p>
												<strong>고기류</strong>
											</p>
											<div class="progress progress-striped active">
												<div class="progress-bar progress-bar-warning"
													role="progressbar" aria-valuenow="${meatSize}" aria-valuemin="0"
													aria-valuemax="${totalSize}">
												</div>
											</div>
										</div>
								</a></li>
								<li class="divider"></li>
								<li><a href="/FmProject/multiList.do?menu=24">
										<div>
											<p>
												<strong>찌개류</strong>
											</p>
											<div class="progress progress-striped active">
												<div class="progress-bar progress-bar-danger"
													role="progressbar" aria-valuenow="${soupSize}" aria-valuemin="0"
													aria-valuemax="${totalSize}">
												</div>
											</div>
										</div>
								</a></li>
								
							</ul> <!-- /.dropdown-tasks --></li>
					</ul>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<c:set var="start" value="${record_start_no}"></c:set>
			
			<c:if test="${record_end_no!=0}">
				<c:set var="end" value="${record_end_no-1}"></c:set>
				<c:forEach var="k" begin="${start}" end="${end}" step="1">
					<c:set var="recipes" value="${recipesList[k]}"></c:set>
					<%-- <c:out value="${recipes.title}"></c:out> --%>
					<c:if test="${k%3==0}"><div class="row"></c:if>
						<div class="col-xs-4">
							<div class="panel panel-warning">
								<div class="panel-heading">
									<div class="row">
										<span class="pull-left">&nbsp;&nbsp;${recipes.title}</span>
										<c:if test="${userVO.id !=null }">
									
											<c:set var="tag" value="0"></c:set>
											<c:forEach var="like" items="${likeList}">
												<c:if test="${like.rNo==recipes.no&&like.isLike=='T'}">
													<c:set var="tag" value="1"></c:set>
												</c:if>
											</c:forEach>
											<c:if test="${tag==1}">
												<span class="pull-right"><a href="/FmProject/multiList.do?no=${recipes.no}&islike=1" role="button"><i class="fa fa-thumbs-up  fa-fw"></i></a>&nbsp;&nbsp;</span>
											</c:if>
											<c:if test="${tag==0}">
												<span class="pull-right"><a href="/FmProject/multiList.do?no=${recipes.no}&islike=0" role="button"><i class="fa fa-thumbs-o-up  fa-fw"></i></a>&nbsp;&nbsp;</span>
											</c:if>
										
										</c:if>
									</div>
								</div>
							<a href="/FmProject/multiDetail.do?no=${recipes.no}">
								<div class="panel-footer">
									<a href="/FmProject/multiDetail.do?no=${recipes.no}"> 
										<img class="img-thumbnail" src="/FmProject/upload/recipes/${recipes.titleImg}" alt="">
									</a>
									<div class="row">
										<div class="text-right text-info">
											<br/>
											<a class="btn btn-warning btn-circle"><i class="fa fa-star fa-1x"></i></a>
											<a class="btn btn-danger btn-circle"><i class="fa fa-heart fa-1x"></i></a>
											<a class="btn btn-info btn-circle"><i class="fa fa-thumbs-up fa-1x"></i></a>
											${recipes.likeCnt}개</div>
									</div>
								</div>
							</a>
							</div>
						</div>
	
					<c:if test="${k%3==2 || k==end}">
						</div>
					</c:if>
				</c:forEach>
			<br/>
			<div class="text-center">
		
				<a href="/FmProject/multiList.do?pageno=1">[맨앞으로]</a> <a
					href="/FmProject/multiList.do?pageno=${prev_pageno }">[이전]</a>
		
				<c:forEach var="kk" begin="${page_sno}" end="${page_eno}" step="1">
					<a href="/FmProject/multiList.do?pageno=${kk}"> <c:choose>
							<c:when test="${pageno==kk}">
								<c:out value="[${kk}]"></c:out>
							</c:when>
							<c:otherwise>
								<c:out value="${kk}"></c:out>
							</c:otherwise>
						</c:choose>
					</a>
					<c:if test="${kk<pageno}">, </c:if>
		
				</c:forEach>
		
				<a href="/FmProject/multiList.do?pageno=${next_page }">[다음]</a> 
				<a href="/FmProject/multiList.do?pageno=${total_page}">[맨뒤로]</a>
			</div>
		</c:if>
		<c:if test="${record_end_no eq 0}">
			<h1><strong>검색결과가 없습니다.</strong></h1>
		</c:if>
		<%@ include file="/jsp/include/bottom.jsp"%>
	</div>
	
	</div>
	<!-- Bootstrap Core JavaScript -->
	<script src="/FmProject/resources/startbootstrap/vendor/bootstrap/js/bootstrap.min.js"></script>
	<!-- Metis Menu Plugin JavaScript -->
	<script src="/FmProject/resources/startbootstrap/vendor/metisMenu/metisMenu.min.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="/FmProject/resources/startbootstrap/dist/js/sb-admin-2.js"></script>
</body>
</html>