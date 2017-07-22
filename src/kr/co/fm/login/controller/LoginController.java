package kr.co.fm.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.fm.core.controller.Controller;
import kr.co.fm.dao.LoginDao;
import kr.co.fm.vo.UsersVo;

public class LoginController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		LoginDao dao = new LoginDao();
		UsersVo uvo = dao.loginCheck(id, password);
		
		if(request.getParameter("ajax")!=null){
			String msg="";
			String success="T";
			if(uvo.getId() == null) {
				msg = "아이디 또는 패스워드가 잘못되었습니다";
				success ="F";
			} else {
				msg = uvo.getType().equals("A")? "관리자님 환영합니다":uvo.getName() + "님 환영합니다";
			}
			request.setAttribute("success", success);
			request.setAttribute("msg", msg);
			System.out.println("ajax check" +uvo );
			return "/jsp/login/LoginCheck.jsp";
		}else{
			request.getSession().setAttribute("userVO", uvo);
			System.out.println(uvo);
			return "redirect:/FmProject/multiList.do";
		}
	}

}
