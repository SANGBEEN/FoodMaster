package kr.co.fm.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.fm.core.controller.Controller;

public class loginModalController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getParameter("redirect")=="signin"){
			request.setAttribute("redirect", "signin");
		}
				
		return "/jsp/login/loginModal.jsp";
	}

}
