package kr.co.fm.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainPaging {
	public static final String TAG = "paging";
	HttpServletRequest request;
	HttpServletResponse response;
	int pageNo=0;
	int totalRecord=0;
	int pagePerCnt=9;
	int pageGroupCnt=5;
	int endNo=0;
	int startNo=0;
	int totalPage = 0;
	
	
	public MainPaging(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
	}

	public void setPageNo(String no){
		pageNo = toInt(no);
		//현재 페이지
		
		if(pageNo<1){
			pageNo = 1;
		}else if(no==null){
			pageNo = 1;
		}
		System.out.println(TAG+pageNo);
	}
	
	public PageVo setTotalRecord(String size){
		totalRecord = toInt(size);
		setExcuteNo();
		setOther();
		PageVo vo = setAttribute();
		return vo;
	}
	
	private void setExcuteNo(){
		endNo = pageNo*pagePerCnt;				
		startNo = endNo-(pagePerCnt-1);
		if(endNo>totalRecord){
			endNo = totalRecord;
		}
		
		totalPage = totalRecord / pagePerCnt + (totalRecord % pagePerCnt>0 ? 1 : 0);
		if(pageNo>totalPage){
			pageNo = totalPage;
		}
	}
	
	int groupNo=0;
	int pageEno=0;
	int pageSno=0;
	int prevPageNo=0;
	int nextPageNo=0;
	
	
	private void setOther(){
		groupNo = pageNo/pageGroupCnt+(pageNo%pageGroupCnt>0 ? 1:0);
		pageEno = groupNo*pageGroupCnt;		
		pageSno = pageEno-(pageGroupCnt-1);	
		if(pageEno>totalPage){	
			pageEno=totalPage;
		}
		
		prevPageNo = pageSno-pageGroupCnt;		
		nextPageNo = pageSno+pageGroupCnt;
		if(prevPageNo<1){		
			prevPageNo=1;
		}
		if(nextPageNo>totalPage){	
			nextPageNo=totalPage/pageGroupCnt*pageGroupCnt+1;	
		}
	}
	
	private PageVo setAttribute() {
		PageVo pv = new PageVo(pageNo, totalPage, pageEno, pageSno, prevPageNo, nextPageNo, startNo-1, endNo);
		return pv;
		 
	}
	
	public Integer toInt(String x){
		int a = 0;
		try{
			a = Integer.parseInt(x);
		}catch(Exception e){}
		return a;
	}
}
