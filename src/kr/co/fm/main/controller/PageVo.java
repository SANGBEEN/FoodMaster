package kr.co.fm.main.controller;

import java.io.Serializable;

public class PageVo implements Serializable{
	private int pageNo=0;
	private int totalPage=0;
	private int pageEno=0;
	private int pageSno=0;
	private int prevPageNo=0;
	private int nextPageNo=0;
	private int startNo=0;
	private int endNo=0;
	
	

	public PageVo(int pageNo, int totalPage, int pageEno, int pageSno, int prevPageNo, int nextPageNo, int startNo,
			int endNo) {
		super();
		this.pageNo = pageNo;
		this.totalPage = totalPage;
		this.pageEno = pageEno;
		this.pageSno = pageSno;
		this.prevPageNo = prevPageNo;
		this.nextPageNo = nextPageNo;
		this.startNo = startNo;
		this.endNo = endNo;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageEno() {
		return pageEno;
	}

	public void setPageEno(int pageEno) {
		this.pageEno = pageEno;
	}

	public int getPageSno() {
		return pageSno;
	}

	public void setPageSno(int pageSno) {
		this.pageSno = pageSno;
	}

	public int getPrevPageNo() {
		return prevPageNo;
	}

	public void setPrevPageNo(int prevPageNo) {
		this.prevPageNo = prevPageNo;
	}

	public int getNextPageNo() {
		return nextPageNo;
	}

	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}

	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PageVo [pageNo=");
		builder.append(pageNo);
		builder.append(", totalPage=");
		builder.append(totalPage);
		builder.append(", pageEno=");
		builder.append(pageEno);
		builder.append(", pageSno=");
		builder.append(pageSno);
		builder.append(", prevPageNo=");
		builder.append(prevPageNo);
		builder.append(", nextPageNo=");
		builder.append(nextPageNo);
		builder.append(", startNo=");
		builder.append(startNo);
		builder.append(", endNo=");
		builder.append(endNo);
		builder.append("]");
		return builder.toString();
	}

	
	
}
