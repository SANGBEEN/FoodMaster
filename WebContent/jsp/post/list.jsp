<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<!-- bxSlider Javascript file -->
<script src="/FmProject/resources/jquery.bxslider/jquery.bxslider.min.js"></script>
<!-- bxSlider CSS file -->
<link href="/FmProject/resources/jquery.bxslider/jquery.bxslider.css" rel="stylesheet" />
<script>
	$(document).ready(function () {
		$('.bxslider').bxSlider({
			 infiniteLoop: false,
			 hideControlOnEnd: true
			});
		var list = ${list};
		var start = 3;
		var end =${list.size()};
		$(document).scroll(function() {
			$('.bxslider').bxSlider({
				 infiniteLoop: false,
				 hideControlOnEnd: true
				});
			var maxHeight = $(document).height();
			var currentScroll = $(window).scrollTop() + $(window).height();
			if (maxHeight <= currentScroll + 100) {
				//console.log(start);
				//console.log(end);
				//console.log(list[start].photolist);
				if(start<end){
					var str ='<div class="col-md-6 col-md-offset-3">';
						str+='	<div class="panel panel-primary">';
						str+='		<div class="panel-heading">'
						str+='			<div class="row">';
						str+='				<div class="col-md-6">';
						str+=list[start].userId;
						str+='				</div>';
						str+='			</div>';
						str+='		</div>';
					///body
						str+='		<div class="panel-body">';
						str+='			<ul class="bxslider">';
						if(list[start].photolist.length!=0){
						for(var i = 0 ; i< list[start].photolist.length;i++){
							str+=' 				<li><img width="100%" class="img-thumbnail" src="/FmProject/upload/posts/'+list[start].photolist[i].url+'"></li>';
						}}
						str+='			</ul>';
						str+='			<div class="row text-left col-xs-12">';
						str+='				<p>'+list[start].content+'</p>';
						str+='	 		</div>';
						str+='			<div class="text-right"><button class="btn btn-outline btn-primary" id="btn'+list[start].no+'">댓글보기</button></div>';
						str+='	 	</div>';
					//footer
						str+='		<div class="panel-footer" id="footer'+ list[start].no +'">';
								
						str+='	 	</div>';
						str+='	 </div>';
						str+='</div>';
						str+='<br/>';
					//console.log(str);
					$('#add-page').append(str);
					start++;
				}
			}
		});
	
		function setCommentField(commLists,post_no){
			var output = '';
			field = "#footer" + post_no;
			var userId = '${userVO.id}';
			for (var i = 0; i < commLists.length; i++) {
				var commObj = commLists[i];
				output += '<div class="col-xs-11 col-xs-offset-1">';
				output += "<strong>" + commObj.userid + "</strong>";
				if (commObj.userid == userId) {
					output += '&nbsp;&nbsp;&nbsp;<a role="button" class="text-danger" id="d'+commObj.no+'">삭제</a>';
				}
				output += '<p class="text-muted">' + commObj.content + '</p>';
				output += '<p class="text-warning">' + commObj.regdate + '</p>';
				output += '<hr class="commentLine"></div>';
				$(field).append(output);
				output = '';
			}
			output += '<div class="form-group input-group">';
			output += '<textarea class="form-control" id="commArea'+post_no+'"';
			output += 'placeholder="댓글을 입력해주세요"> </textarea>';
			output += '<span class="input-group-addon" role="button" id="reg'+post_no+'">댓글등록</span>';
			output += '</div>';
			$(field).append(output);
			
		}
		
		function selectList(post_no){
			
			var reset = "#footer" + post_no;
			$(reset).html("");
			$.ajax({
				url : '/FmProject/comment.do',
				method : 'post',
				contentType : 'application/x-www-form-urlencoded',
				data : {
					'postNo': post_no
				},
				success : function(data){
					//5.응답결과를 화면에 출력
						var commLists = eval(data);
						setCommentField(commLists,post_no);
				}
			});
		}
		//댓글보기
		var state=[];
		$(document).on("click",".btn.btn-outline.btn-primary",function(){
			var post_no = $(this)[0].id;
			post_no = post_no.substring(3);
			if(state.indexOf($(this)[0].id) == -1){
				state.push($(this)[0].id);
				//댓글 서치 ajax
				selectList(post_no);
			}else{
				var reset = "#footer" + post_no;
				$(reset).html("");
				state.splice(state.indexOf($(this)[0].id),1);
			}
		});
		//등록 버튼 클릭시
		$(document).on("click",".input-group-addon",function(){
			var pNo = $(this)[0].id;
			pNo = pNo.substring(3);
			//새로운 댓글 등록시
			var textId = "commArea"+pNo;
			var text = document.getElementById(textId).value;
			if('${userVO.id}'==""){
				alert('로그인 이후에 등록이 가능합니다.');
				return;
			}else if($('#commArea').val() == ""){
				alert('댓글 내용이 없습니다.');
				return;
			}
			text = text.split("\n").join('<br/>');
			$.ajax({
				url : '/FmProject/comment.do',
				method : 'post',
				contentType : 'application/x-www-form-urlencoded',
				data : {
					'comment' : 'true',
					'postNo': pNo,
					'userId' : '${userVO.id}',
					'content': text,
					'parentNo' : '0'
				},
				success : function(data){
					//5.응답결과를 화면에 출력
					//댓글 서치 ajax
					selectList(pNo);
				}
			});
		});
		
		//댓글삭제
		$(document).on("click",".text-danger",function(){
			if(!confirm('삭제하시겠습니까?')){
				return;
			}
			var pNo=$(this)[0].id.substring(1);
			$.ajax({
				url : '/FmProject/commentDel.do',
				method : 'post',
				contentType : 'application/x-www-form-urlencoded',
				data : {
					'no' : $(this)[0].id.substring(1),
					'postNo': pNo
				},
				success : function(data){
					//5.응답결과를 화면에 출력
					var node = "#d"+pNo;
					$(node)[0].parentNode.remove();
					
					var commLists = eval(data);
					setCommentField(commLists);
				}
			});
		});
	});
</script>
<style type="text/css">
hr.commentLine {
    height: 30px;
    border-style: solid;
    border-color: black;
    border-width: 1px 0 0 0;
    border-radius: 20px;
}
hr.commentLine:before { /* Not really supposed to work, but does */
    display: block;
    content: "";
    height: 30px;
    margin-top: -31px;
    border-style: solid;
    border-color: black;
    border-width: 0 0 1px 0;
    border-radius: 20px;
}
</style>

<script>
	
</script>
</head>
<body>
<div id="wrapper" style="background-image : url('/FmProject/resources/images/side.jpg');">
		<%@ include file="/jsp/include/topBar.jsp"%>
		<%@ include file="/jsp/include/sideBar.jsp"%>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">${userVO.id}</h1>
				</div>
			</div>
			<input type="hidden" id="maxSize" value="" />
			<c:set var = "start" scope="request">0</c:set>
			<c:forEach var="post" items="${list }" begin="0" end="2">
				<div class="col-md-6 col-md-offset-3">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="row">
								<div class="col-md-6">
									${post.userId}
								</div>
							</div>
						</div>
						<div class="panel-body">
							<ul class="bxslider">
								<c:forEach var="photo" items="${post.photolist}" >
									<li><img width="100%" class="img-thumbnail" src="/FmProject/upload/posts/${photo.url}" alt=""></li>
								</c:forEach>
							</ul>
							<div class="row text-left col-xs-12">
								<p>${post.content}</p>
								<div class="text-right"><button class="btn btn-outline btn-primary" id="btn${post.no}">댓글보기</button></div>	
							</div>
						</div>
						<div class="panel-footer" id="footer${post.no}">
							
						</div>
					</div>
				</div> 
		
			
			</c:forEach>
			<div class="row">
				<div id="add-page">
				
				</div>
			</div>
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