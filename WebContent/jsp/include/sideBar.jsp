<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	
	$(function() {
		$('#loginModal').click(function() {
			$('div.modal').modal({
				remote : '/FmProject/loginModal.do'
			});
		});
		if('${param.redirect}'=='signin'){
		
			$('div.modal').modal({
				remote : '/FmProject/loginModal.do?redirect=signin'
			});
		}
	});
	//scroll the message box to the top offset of browser's scrool bar  
    $(window).scroll(function()  
    {  
        $(".navbar-default.sidebar").animate({top:$(window).scrollTop()+"px" },{queue: false, duration: 350});    
    });  
    //when the close button at right corner of the message box is clicked   
    $(".navbar-default.sidebar").click(function()  
    {  
        //the messagebox gets scrool down with top property and gets hidden with zero opacity   
        $(".navbar-default.sidebar").animate({ top:"+=15px",opacity:0 });  
    });  

</script>

	<div class="navbar-default sidebar" role="navigation" >
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				<li><a href="/FmProject/multiList.do"><i class="fa fa-th fa-fw"></i>
						Home</a></li>
				<c:if test="${userVO!=null && userVO.type=='A'}">
					<li><a href="#"><i class="fa fa-youtube-square fa-fw"></i>Recipe<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a role="button" href="/FmProject/adminWrite.do?category_no=21">Rice</a></li>
								<li><a role="button" href="/FmProject/adminWrite.do?category_no=22">Noodle</a></li>
								<li><a role="button" href="/FmProject/adminWrite.do?category_no=23">Meat</a></li>
								<li><a role="button" href="/FmProject/adminWrite.do?category_no=24">Soup</a></li>
						</ul>
					</li>
				</c:if>
				<li><a role="button" href="/FmProject/post/list.do"><i class="fa fa-group fa-fw"></i>TimeLine</a></li>
				<c:if test="${userVO!=null}">
					<li><a role="button" href="/FmProject/post/myList.do"><i class="fa fa-photo fa-fw"></i>Post</a></li>
					<li><a role="button" href="/FmProject/myPage.do"><i class="fa fa-info fa-fw"></i>My page</a></li>
					<li><a role="button" href="/FmProject/logout.do" onclick="return confirm('로그아웃 하시겠습니까?');" ><i class="fa fa-user fa-fw"></i>Logout</a></li>
				</c:if>
				<c:if test="${userVO==null}">
					<li><a role="button" id="loginModal"><i class="fa fa-user fa-fw"></i>Login</a></li>
				</c:if>
				<li class="sidebar-search">
					<form action="/FmProject/multiList.do" method="post">
						<div class="input-group custom-search-form">
							<input type="text" class="form-control" placeholder="Search..." name="keyWord" id="searchInput">
							<span class="input-group-btn">
								<button class="btn btn-default" type="submit" id="searchBtn">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div> <!-- /input-group -->
					</form>
				</li>
			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->
</nav>
<!-- Modal -->
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<div class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
    
    </div>
  </div>
</div>
