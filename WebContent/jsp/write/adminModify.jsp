<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	<!-- Morris Charts CSS -->
	<link href="/FmProject/resources/startbootstrap/vendor/morrisjs/morris.css" rel="stylesheet">
	<!-- Custom Fonts -->
	<link href="/FmProject/resources/startbootstrap/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<!-- jQuery -->
	<script src="/FmProject/resources/startbootstrap/vendor/jquery/jquery.min.js"></script>
	 <script>
		function doWrite(){
			
			var w = document.writeForm;
			
			if(w.title.value==""){
				alert("제목을 입력하세요");
				w.title.focus();
				return false;
			}
			
			if(w.video.value == ""){
				alert("비디오 키를 입력하세요");
				w.video.focus();
				return false;
			}
			
			if(w.content.value == ""){
				alert("내용를 입력하세요");
				w.content.focus();
				return false;
			}
	
			
			//파일 확장자 체크
			if(checkExt(w.attachfile)) { //document.writeform.attatchfile1/2
				return false;
			}	
			
			return true;
		}
		
		function checkExt(obj){
			var forbidName = ['exe','java','jsp','js','class','css', 'txt', 'pdf'];
			var fileName = obj.value;
			var index = fileName.lastIndexOf('.');
			var ext = fileName.substring(index+1);
			console.log(ext);
			
			for(var i=0; i<forbidName.length; i++){
				if(forbidName[i] == ext){
					alert(ext + '확장자는 파일 업로드 정책에 위배됩니다');
					document.writeForm.attachfile.focus();
					return true;
				}
			}
			return false;
		}
		$(function(){
			var text = '${recipeObject.content}';
			text = text.split("<br/>").join('\n');
			$('textarea').append(text);
			$(':reset').click(function(){
				history.go(-1);
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
                    <c:choose>
                       <c:when test="${recipeObject.categoryNo eq 21}">
                          <h1 class="page-header">RICE 레시피 작성</h1>
                       </c:when>   
                          <c:when test="${recipeObject.categoryNo eq 22}">
                          <h1 class="page-header">NOODLE 레시피 작성</h1>
                       </c:when>   
                          <c:when test="${recipeObject.categoryNo eq 23}">
                          <h1 class="page-header">MEAT 레시피 작성</h1>
                       </c:when>   
                          <c:when test="${recipeObject.categoryNo eq 24}">
                          <h1 class="page-header">SOUP 레시피 작성</h1>
                       </c:when>   
               </c:choose>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-6 col-lg-offset-3">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                        	레시피 작성
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form role="form"  action="<%=request.getContextPath()%>/adminModifyProcess.do" method="post" name="writeForm" onsubmit="return doWrite()" enctype="multipart/form-data" >
                                        <div class="form-group">
                                            <label>제목</label>
                                            <input class="form-control" name="title" value="${recipeObject.title}">
                                        </div>
                                        <div class="form-group">
                                            <label>비디오</label>
                                            <input class="form-control" placeholder="키 입력" name="video" value="${recipeObject.video}">
                                        </div>
                                        <div class="form-group">
                                            <label>내용을 입력하세요</label>
                                            <textarea class="form-control" rows="5" name="content"></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label>사진 선택</label>
                                            <script>
                                            $(document).on("click","#file",function(){
                                            	if(confirm('사진을 변경하시겠습니까?')){
                                            		$('img')[0].remove();
                                            		$('#preImg').remove();
                                            	}else{
                                            		return false;
                                            	}
                                            });
                                            </script>
                                            <div>
                                            	<img style="width:100px;height:100px"src="/FmProject/upload/recipes/${recipeObject.titleImg}">
                                            </div>
                                            <div class="form-group input-group">
                                            	<input id="file" type="file" name="attachfile" class="form-control">
                                            	<span class="input-group-addon" role="button"><i class="fa fa-refresh"></i></span>
                                            </div>
                                        </div>
                                        <input type="hidden" name="no" value="${param.no }">
                                        <input type="hidden" name="category_no" value="${recipeObject.categoryNo}">
                                        <input type="hidden" name="preImg" id="preImg" value="${recipeObject.titleImg}">
                                        <button type="submit" class="btn btn-default">확인</button>
                                        <button type="reset" class="btn btn-default">취소</button>
                                    </form>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->
        <%@ include file="/jsp/include/bottom.jsp"%>
    </div>
    <!-- /#wrapper -->

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

