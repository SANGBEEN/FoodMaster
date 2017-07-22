<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html">
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
	
<script src="/FmProject/jsp/main/js/jquery.mCustomScrollbar.concat.min.js"></script>
<link href="/FmProject/jsp/main/js/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>

<style type="text/css">

@import 'https://fonts.googleapis.com/css?family=Roboto';
 
.youtube-list{
  overflow-x: auto;
  white-space: nowrap;
}
 
.youtube-list .item{
  display: inline-block;
  height: 200px;
  margin: 5px;
}


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
	function setCommentField(commLists, pno){
		$('#accordion').html("");
		var output = '';
		var userId = '${userVO.id}';
		for (var i = 0; i < commLists.length; i++) {
			var commObj = commLists[i];
			if(commObj.parentno == 0){
				output += '<div class="panel panel-default">';
				output += '<div class="panel-heading">';
				output += "<strong>" + commObj.userid + "</strong>";
				if(commObj.userid == userId){
					output += '&nbsp;&nbsp;&nbsp;<a role="button" class="text-danger" id="d'+commObj.no+'">삭제</a>';
				}
				output += '<p class="text-muted">' + commObj.content + '</p>';
				output += '<p class="text-warning">' + commObj.regdate + '</p>';
				output += '<a data-toggle="collapse" data-parent="#accordion" href="#collapse'+commObj.no +'">';
				output += '<button type="button" class="btn btn-outline btn-success">댓글보기</button></a>';
				output += '&nbsp;<button type="button" class="btn btn-outline btn-warning" id="'+commObj.no+'">답변달기</button>';
				output += '</a></div>';
				output += '<div id="collapse'+ commObj.no +'" class="panel-collapse collapse" style="height: 0px;">';
				output += '<div class="panel-body" id="parentno'+ commObj.no +'">';
				output += '</div>';
				output += '</div>';
				output += '</div>';
				$('#accordion').append(output);
				output = '';
			}else{
				output += '<div class="col-xs-11 col-xs-offset-1">';
				output += "<strong>" + commObj.userid + "</strong>";
				if(commObj.userid == userId){
					output += '&nbsp;&nbsp;&nbsp;<a role="button" class="text-danger" id="d'+commObj.no+'">삭제</a>';
				}
				output += '<p class="text-muted">' + commObj.content + '</p>';
				output += '<p class="text-warning">' + commObj.regdate + '</p>';
				output += '<hr class="commentLine"></div>';
				var parentDIV = "#parentno"+commObj.parentno;
				$(parentDIV).append(output);
				output = '';
			}
		}
	}
</script>
<script type="text/javascript">
	$(function(){
		//페이지 로드시
		$.ajax({
			url : '/FmProject/comment.do',
			method : 'post',
			contentType : 'application/x-www-form-urlencoded',
			data : {
				'rNo': '${param.no}',
			},
			success : function(data){
				//5.응답결과를 화면에 출력
				var commLists = eval(data);
				setCommentField(commLists);
			}
		});
		var query = '${detailObject.title}';
		query = query.split(" ")[0];
		$.ajax({
			  dataType: "json",//데이터타입
			  url: //요청할 주소
			    'https://www.googleapis.com/youtube/v3/search'+
			    '?part=snippet'+
			    '&maxResults=10'+
			    '&order=viewCount'+
			    '&regionCode=KR'+
			    '&q='+encodeURIComponent(query)+
			    '&type=video'+
			    '&videoDefinition=high'+
			    '&key=AIzaSyChPQ7wyJdU2QcGXf3DJqeqAy4uHhdRdLA'
			}).done(function(data){
			    /* Initial */
			    //alert(data);
			    console.log(data);
			    var tag = document.createElement('script');
			    tag.src = "https://www.youtube.com/iframe_api";
			    var firstScriptTag = document.getElementsByTagName('script')[0];
			    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

			    var player;
			    
			   /*  onYouTubeIframeAPIReady = function(event){
			        player = new YT.Player('youtube-player', {
			            videoId: data.items[0].id.videoId
			        });
			    } */
			    
			   /*  $('#video-title').text(data.items[0].snippet.title); */
			    $('#youtube-list').mCustomScrollbar({
			    	axis:'x',
			   		setHeight: 'auto',
			    	setWidth: 'auto',
			    	setTop: '300px'
			    	});
			    
			    /* List */
			    var length = data.items.length;
			    
			    for(var i=0; i<length; i++){
			        var item = data.items[i];
			        var videoThumb = item.snippet.thumbnails.medium.url;
			        var videoTitle = item.snippet.title;
			      /*   li = '<li><img src="'+videoThumb+'" class="thumb">'
			        + '<p class="title"><span class="outer"><span class="inner">'
			        +videoTitle+'</span></span></p></li>'; */
			        li = '<img src="'+videoThumb+'" class="item">';
			        $('#youtube-list').append(li);

					$('#mCSB_1').remove();
			    }
			 });
		
		
		
		//답변 버튼 클릭시
		var state=[];
		$(document).on("click",".btn.btn-outline.btn-warning",function(){
			if(state.indexOf($(this)[0].id) == -1){
				var appendText ='<div class="form-group input-group">';
				appendText += '<textarea type="text" class="form-control" id="reCommArea'+$(this)[0].id+'" placeholder="답변을 입력해주세요"></textarea>';
				appendText += '<span role="button" class="input-group-addon" id="p'+$(this)[0].id+'">답변등록</span></div>';
				$(this).after(appendText);
				state.push($(this)[0].id);
			}else{
				var id = "#p"+$(this)[0].id;
				$(this).next().remove();
				state.splice(state.indexOf($(this)[0].id),1);
			}
		});
		
		//등록 버튼 클릭시
		$(document).on("click",".input-group-addon",function(){
			if($(this)[0].id == "reg"){
				//새로운 댓슬 등록시
				if('${userVO.id}'==""){
					alert('로그인 이후에 등록이 가능합니다.');
					return;
				}else if($('#commArea').val() == ""){
					alert('댓글 내용이 없습니다.');
					return;
				}
				var text = document.getElementById('commArea').value;
				text = text.split("\n").join('<br/>');
				$.ajax({
					url : '/FmProject/comment.do',
					method : 'post',
					contentType : 'application/x-www-form-urlencoded',
					data : {
						'comment' : 'true',
						'rNo': '${param.no}', 
						'userId' : '${userVO.id}',
						'content': text,
						'parentNo' : '0'
					},
					success : function(data){
						//5.응답결과를 화면에 출력
						var commLists = eval(data);
						setCommentField(commLists);
					}
				});
			}else{
				//답변 등록시
				var pno = $(this)[0].id;
				pno = pno.substring(1);
				var areaId = "reCommArea"+pno;
				var text = document.getElementById(areaId).value;
				text = text.split("\n").join('<br/>');
				if('${userVO.id}'==""){
					alert('로그인 이후에 등록이 가능합니다.');
					return;
				}else if(text ==""){
					alert('답변 내용이 없습니다.');
					return;
				}
				$.ajax({
				url : '/FmProject/comment.do',
				method : 'post',
				contentType : 'application/x-www-form-urlencoded',
				data : {
					'comment' : 'true',
					'rNo': '${param.no}', 
					'userId' : '${userVO.id}',
					'content': text,
					'parentNo' : pno
				},
				success : function(data,pno){
					//5.응답결과를 화면에 출력
					var commLists = eval(data);
					setCommentField(commLists);
					
				}
				});
			}
			//등록성공시 답변창 사라지면서 유지된 state초기화
			state=[];
		});
		//댓글삭제
		$(document).on("click",".text-danger",function(){
			if(!confirm('삭제하시겠습니까?')){
				return;
			}
			//alert($(this)[0].id.substring(1));
			$.ajax({
				url : '/FmProject/commentDel.do',
				method : 'post',
				contentType : 'application/x-www-form-urlencoded',
				data : {
					'no' : $(this)[0].id.substring(1),
					'rNo': '${param.no}'
				},
				success : function(data){
					//5.응답결과를 화면에 출력
					var node = "#d"+no;
					$(node)[0].parentNode.remove();
				}
			});
		});
			
	});
	
</script>
</head>
<body>

<div id="wrapper" style="background-image : url('/FmProject/resources/images/side.jpg');">
		<%@ include file="/jsp/include/topBar.jsp"%>
		<%@ include file="/jsp/include/sideBar.jsp"%>
		<div id="page-wrapper">
			 <div class="row">
                <div class="col-lg-12 text-center" >
                    <h1 id="dheader" class="page-header">${detailObject.title}<div class="text-right small">조회수 ${detailObject.viewCnt}</div></h1>
                    <c:set scope="session" var="recipeObject" value="${detailObject}" />
                    <script>
                    	if('${userVO.type}' == 'A'){
                    		var modiurl = '/FmProject/adminModifyProcess.do?no='+ ${param.no};
                    		var delurl = '/FmProject/adminDelProcess.do?no='+ ${param.no};
	                    	$('#dheader').append(
	                    			'<a id="modify" type="button" class="btn btn-outline btn-info">수정</a>');
	                    	$('#dheader').append(
	                    			'<a id="del" type="button" class="btn btn-outline btn-danger">삭제</a>');
                    		$('#modify').attr('href',modiurl);
                    		$('#del').attr('href',delurl);
                    		$('#del').attr('onclick','return confirm("삭제 하시겠습니까?");');
                    	}
                    </script>
                </div>
                <!-- /.col-lg-12 -->
            </div>
			<div class="col-md-7">
				<iframe width="100%" height="480px"
					src="https://www.youtube.com/embed/${detailObject.video}"
					frameborder="0" allowfullscreen></iframe>
			</div>
			<div class="col-md-5" style="max-height:480px; overflow:auto;" >
				<p>${detailObject.content}</p>
			</div>
			
			 <div class="row">
                <div class="col-xs-12">
                    <div class="panel panel-info">
                        <!-- .panel-heading -->
                        <div class="panel-body">
                            <div class="panel-group" id="accordion">
                            </div>
                        </div>
                        <!-- .panel-body -->
						<div class="panel-footer">
							<div class="form-group input-group">
								<textarea class="form-control" id="commArea"
									placeholder="댓글을 입력해주세요"> </textarea>
								<span class="input-group-addon" role="button" id="reg">댓글등록</span>
							</div>
						</div>
					</div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <div class="row" >
				<div class="col-xs-12">
					<div id="youtube-list" class="youtube-list" style="height: 200px;">
					</div>
				</div>
			</div>
			<%@ include file="/jsp/include/bottom.jsp"%>
			
			<%-- 
			<div class="row">
					<ul class="wrap" id="comments">
					
					<c:if test='${commList!=null}'>
						<c:forEach var='comm' items='${commList}'>
							<p>${comm.userId}</p>
							<div class="col-xs-9"><h4>${comm.content}</h4></div>
							<div class="col-xs-3">
								<button id="">답변달기</button>
							</div>
						</c:forEach>
					</c:if>
					
					</ul>
			</div>
			 --%>
			
		</div>
	</div>
	
	<%-- <h2>${detailObject.video}</h2> --%>
	<!-- iframe은 외부문서를 삽입하는 태그  -->

	<!-- Bootstrap Core JavaScript -->
	<script
		src="/FmProject/resources/startbootstrap/vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="/FmProject/resources/startbootstrap/vendor/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="/FmProject/resources/startbootstrap/dist/js/sb-admin-2.js"></script>
</body>
</html>