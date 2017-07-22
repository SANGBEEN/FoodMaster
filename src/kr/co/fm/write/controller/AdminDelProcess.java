package kr.co.fm.write.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.fm.core.controller.Controller;
import kr.co.fm.dao.WriteDao;
import kr.co.fm.vo.RecipesVo;

public class AdminDelProcess implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		int recipeNo = Integer.parseInt(request.getParameter("no"));
		
		WriteDao dao = new WriteDao();
		dao.recipeDelete(recipeNo);
		
		return "redirect:/FmProject/multiList.do";
	}
}
