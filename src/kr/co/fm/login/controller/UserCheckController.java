package kr.co.fm.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.fm.core.controller.Controller;
import kr.co.fm.dao.LoginDao;
import kr.co.fm.dao.UserDao;
import kr.co.fm.vo.UsersVo;

public class UserCheckController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserDao udao = new UserDao();
		UsersVo uvo= udao.userCheck(request.getParameter("id"));
		String msg = uvo.getId()==null? "가입할수 있는 아이디입니다.":"이미 존재하는 아이디입니다.";
		String success = uvo.getId()==null? "Y":"N";
		request.setAttribute("success", success);
		request.setAttribute("msg", msg);
		return "/jsp/login/userCheck.jsp";
	}
}
