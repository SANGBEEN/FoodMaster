package kr.co.fm.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.fm.core.controller.Controller;
import kr.co.fm.dao.LoginDao;
import kr.co.fm.vo.UsersVo;

public class NaverLoginCheckController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		
		LoginDao dao = new LoginDao();
		UsersVo uvo = dao.naverLoginCheck(email);
		
		String msg = "";
		String success = "T";
		if (uvo.getId() == null) {
			msg = "회원가입이 필요합니다.";
			success = "F";
			HttpSession session = request.getSession();
			session.setAttribute("naverEmail", email);
			session.setAttribute("naverName", name);
		} else {
			msg = uvo.getType().equals("A")? "관리자님 환영합니다":uvo.getName() + "님 환영합니다";
			request.getSession().setAttribute("userVO", uvo);
		}
		request.setAttribute("success", success);
		request.setAttribute("msg", msg);
		return "/jsp/login/naverCheck.jsp";
	}
}
