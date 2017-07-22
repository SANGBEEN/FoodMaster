<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src=/FmProject/resources/js/loginForm.js></script>

<!-- header -->
<div class="modal-header">
	<!-- 닫기(x) 버튼 -->
	<button type="button" class="close" data-dismiss="modal">x</button>
	<!-- header title -->
	<h4 class="modal-title">Login Dialog</h4>
</div>
<!-- body -->
<div class="modal-body">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Login</h3>
				</div>
				<div class="panel-body">
					<form action="/FmProject/login.do" method="post" name="lform">
						<div class="col-xs-12">
							<fieldset class="col-xs-12">
									<div class="form-group">
										<input class="form-control" placeholder="ID" id="id" name="id" type="text" autofocus>
									</div>
									<div class="form-group">
										<input class="form-control" placeholder="Password" name="password" id="password" type="password">
									</div>
							</fieldset>
						</div>
							<!-- Change this to a button or input when using this as a form -->
							<div class="col-xs-6"><a id="loginBtn" role="button" class="btn btn-lg btn-info btn-block">Login</a></div>
							<div class="col-xs-6"><a id="signupBtn" role="button" class="btn btn-lg btn-info btn-block">Sign Up</a></div>
							<script>
							if('${param.redirect}'=='signin'){
								$('.modal-body .panel-title').text('Sign Up');
								$('fieldset').attr('class','col-xs-9');
								$('fieldset').after('<div id="checkList" class="col-xs-3"></div>');
								$('#checkList').append('<div class="form-group"><a id="idCheck" role="button" class="btn btn-default btn-block">Check</a></div">');
								$('#checkList').append('<div class="form-group text-center" id="passwordAble"></div>');
								$('fieldset').append('<div class="form-group"><input class="form-control" placeholder="Password Confirm" id="passwordConfirm" type="password"><div>');
								$('fieldset').append('<div class="form-group"><input class="form-control" placeholder="Email" id="email" type="email"><div>');
								$('fieldset').append('<div class="form-group"><input class="form-control" placeholder="Name" id="name" type="text"></div>');
								$('#email').val('${naverEmail}');
								$('#name').val('${naverName}');
								if(confirm('네이버 로그인과 동일한 아이디를 사용하시겠습니까?') == true){
									var id = '${naverEmail}';
									$('#id').val(id.split("@")[0]);
								}
							}
							</script>
					</form>
				</div>
			</div>
			
			<div class="col-md-6 col-md-offset-4">
				<div id="naver_id_login"></div>
				<!-- //네이버아이디로로그인 버튼 노출 영역 -->
				<script type="text/javascript">
					var naver_id_login = new naver_id_login(
							"oUnaGJTjhgtEudPIeXgL",
							"http://52.78.17.198:8080/FmProject/naverModal.do");
					var state = naver_id_login.getUniqState();
					naver_id_login.setButton("green", 3, 40);
					naver_id_login
							.setDomain("http://52.78.17.198:8080/FmProject/naverModal.do");
					naver_id_login.setState(state);
					naver_id_login.setPopup();
					naver_id_login.init_naver_id_login();
				</script>
			</div>

		</div>
	</div>
</div>
<!-- Footer -->
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
</div>