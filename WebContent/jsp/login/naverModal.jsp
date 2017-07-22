<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%request.setCharacterEncoding("UTF-8");%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- Bootstrap Core CSS -->
	<link href="/FmProject/resources/startbootstrap/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src=/FmProject/resources/js/loginForm.js></script>
<script src="/FmProject/resources/startbootstrap/vendor/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<script type="text/javascript">
		var naver_id_login = new naver_id_login("oUnaGJTjhgtEudPIeXgL", "http://52.78.17.198:8080/FmProject/naverModal.do");
		// 접근 토큰 값 출력
		//alert(naver_id_login.oauthParams.access_token);
		// 네이버 사용자 프로필 조회
		naver_id_login.get_naver_userprofile("naverSignInCallback()");
		// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
		function naverSignInCallback() {
			alert('인증되었습니다.');
			//alert('email : ' + naver_id_login.getProfileData('email'));
			//alert('name : ' + naver_id_login.getProfileData('name'));
			$.ajax({
					url : '/FmProject/naverCheck.do',
					type : 'post',
					data : {
						'email' : naver_id_login.getProfileData('email'),
						'name' : naver_id_login.getProfileData('name')
					},
					contentType : 'application/x-www-form-urlencoded',
					success : function(data){
						var obj = JSON.parse(data)
							alert(obj[0]['msg']);
						if(obj[0]['success'] == "F"){
							opener.parent.location='/FmProject/multiList.do?redirect=signin';
							window.close();
						}else{
							opener.parent.location='/FmProject/multiList.do';
							window.close();
						}
					}
				});
			
		}
	</script>
</body>
</html>