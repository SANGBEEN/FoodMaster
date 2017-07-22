package kr.co.fm.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.fm.core.controller.Controller;
import kr.co.fm.dao.LoginDao;
import kr.co.fm.dao.UserDao;
import kr.co.fm.vo.UsersVo;

public class CreateUserController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		UsersVo uvo = new UsersVo();
		uvo.setId(request.getParameter("id"));
		uvo.setPassword(request.getParameter("password"));
		uvo.setEmail(request.getParameter("email"));
		uvo.setName( request.getParameter("name"));
		UserDao udao = new UserDao();
		System.out.println("create..... " + uvo);
		udao.insertUser(uvo);
		
		request.setAttribute("msg", "가입되었습니다.");
		return "/jsp/login/userCheck.jsp";
	}
}
