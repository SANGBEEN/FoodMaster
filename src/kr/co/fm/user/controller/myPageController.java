package kr.co.fm.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;

import kr.co.fm.core.controller.Controller;
import kr.co.fm.dao.UserDao;
import kr.co.fm.vo.UsersVo;

public class myPageController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
/*		int userNO = Integer.parseInt(request.getParameter("no"));
		
		
		UserDao uDao = new UserDao();
		UsersVo uvo = uDao.selectUserDetail(userNO);
		request.setAttribute("", arg1);
		*/
		return "/jsp/user/myPage.jsp";
	}
}
