package kr.co.fm.main.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.fm.core.controller.Controller;
import kr.co.fm.dao.LikeDao;
import kr.co.fm.dao.MainDao;
import kr.co.fm.vo.LikeVo;
import kr.co.fm.vo.RecipesVo;
import kr.co.fm.vo.UsersVo;

//<iframe width="560" height="315" src="https://www.youtube.com/embed/iWZU6MJ90o4" frameborder="0" allowfullscreen></iframe>

public class MultiListController implements Controller{
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		/*select 동작*/
		MainDao mDao = new MainDao();
		LikeDao lDao = new LikeDao();
		List<RecipesVo> list = null;
		List<LikeVo> likeList = null;
		/*paging 동작*/
		MainPaging mp = new MainPaging(request, response);
		String menuNum = request.getParameter("menu");
		String searchText = request.getParameter("keyWord");
		System.out.println("test>>>>>>>>>>>>>>>>>"+searchText);
		/*조회 trans*/
		if(menuNum==null&&searchText==null){
			//전체 조회
			list = mDao.selectAllList();
			request.getSession().setAttribute("totalSize", mDao.countList(0));
			request.getSession().setAttribute("riceSize", mDao.countList(21));
			request.getSession().setAttribute("noodleSize", mDao.countList(22));
			request.getSession().setAttribute("meatSize", mDao.countList(23));
			request.getSession().setAttribute("soupSize", mDao.countList(24));
		}else if(menuNum!=null){
			//메뉴에 따른 조회
			int mNum = Integer.valueOf(menuNum);
			list = mDao.selectMenuList(mNum);
		}else if(searchText!=null){
			list = mDao.selectSearchList(searchText);
			
		}
		request.setAttribute("recipesList", list);
		//page번호 설정
		/*if(request.getParameter("pageno").equals(null)){
			System.out.println("sibar");
			request.setAttribute("pageno", "1");
		}*/
		System.out.println("pageNo1"+request.getParameter("pageno"));
		mp.setPageNo(request.getParameter("pageno")); 
		PageVo vo = mp.setTotalRecord(String.valueOf(list.size()));
		request.setAttribute("prev_pageno", vo.getPrevPageNo());
		request.setAttribute("page_sno", vo.getPageSno());
		request.setAttribute("page_eno", vo.getPageEno());
		request.setAttribute("pageno", vo.getPageNo());
		request.setAttribute("next_pageno", vo.getNextPageNo());
		request.setAttribute("total_page", vo.getTotalPage());
		request.setAttribute("record_start_no", vo.getStartNo());
		request.setAttribute("record_end_no", vo.getEndNo());
		
		
		
		UsersVo userVo = (UsersVo)request.getSession().getAttribute("userVO");
		if(userVo!=null){
			String userId = userVo.getId();
			likeList = lDao.selectInitLikeList(userId);
			request.setAttribute("likeList", likeList);
			System.out.println(userId);
		}
		
		String num = request.getParameter("no");
		
		if(num!=null&&userVo!=null){
			boolean tag = false;
			int iNum = Integer.valueOf(num);
			for(int i=0;i<likeList.size();i++){
				if((likeList.get(i).getrNo())==iNum){
					tag = true;
					break;
				}
			}
			String userId = userVo.getId();
			String isLike = request.getParameter("islike");
			int iIsLike = Integer.valueOf(isLike);
			if(tag){
				int x = lDao.updateLike(userId, iNum, iIsLike);
				if(x>0){
					likeList = lDao.selectInitLikeList(userId);
					request.setAttribute("likeList", likeList);
					lDao.updateLike(iNum, iIsLike);
				}
			}else{
				int y = lDao.insertLike(userId, iNum, iIsLike);
				
				if(y>0){
					likeList = lDao.selectInitLikeList(userId);
					request.setAttribute("likeList", likeList);
					lDao.updateLike(iNum, iIsLike);
				}
			}
			System.out.println(num+": "+isLike);
			return "redirect:/FmProject/multiList.do";
		}
		return "/jsp/main/multiList.jsp";
	}

}
