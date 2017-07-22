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
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="jQuery.MultiFile.min.js" type="text/javascript" language="javascript"> </script>
	 <script>
	 $(function(){
		 $('#cancel').click(function(){
		 		history.go(-1);
		 	});
		function doWrite(){
			
			var w = document.writeForm;
			
			if(w.content.value == ""){
				alert("내용를 입력하세요");
				w.content.focus();
				return false;
			}
	
			if(w.attachfile.value == ""){
				alert("사진을 선택하세요");
				w.attachfile.focus();
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
		
	 });
	 $(function(){
		$('button[type = submit]').click(function(){
			var text = $('textarea[name = content]').val();
			text = text.split("\n").join('<br/>');
			var tmp1 = text.substring(0,text.indexOf("#"));
			var tmp2 = text.substring(text.indexOf("#"), text.indexOf(" ",text.indexOf("#")));
			var tmp3 = text.substring(text.indexOf(" ",text.indexOf("#")));
			text = tmp1 + "<a>" + tmp2 + "</a>"+tmp3;
			
			$('textarea[name = content]').val(text);
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
                    <h1 class="page-header">포스트 작성</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-6 col-lg-offset-3">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                        	포스트 작성
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                
                                    <form role="form"  action="<%=request.getContextPath()%>/post/write.do" method="post" name="writeForm" onsubmit="return doWrite()" enctype="multipart/form-data" >
                                        <div class="form-group">
                                            <label>내용을 입력하세요</label>
                                            <textarea class="form-control" rows="5" name="content"></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label>사진 선택</label>
                                           <!--  <input type="file" name="attachfile"> -->
                                           <input type="file" name="file" multiple  />
                                        </div>
                                        <button type="submit" class="btn btn-default">확인</button>
                                        <button type="reset" id="cancel" class="btn btn-default">취소</button>
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
