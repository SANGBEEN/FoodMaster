package kr.co.fm.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.fm.core.controller.Controller;

public class LogoutProcess implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().removeAttribute("userVO");
		
		return "redirect:/FmProject/multiList.do";
	}

}
