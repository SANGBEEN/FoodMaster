package kr.co.fm.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.fm.core.controller.Controller;
import kr.co.fm.dao.MainDao;

public class MultiListDetailController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no = Integer.valueOf(request.getParameter("no"));
		System.out.println(no);
		MainDao mDao = new MainDao();
		request.setAttribute("detailObject", mDao.selectDetailObject(no));
		return "/jsp/main/detail.jsp";
	}
}
