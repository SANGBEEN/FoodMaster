package kr.co.fm.vo;

import java.io.Serializable;

public class PhotosVo implements Serializable{
	private int no;
	private int postNo;
	private String url;
	private String regDate;
	
	
	public PhotosVo() {
		super();
	}

	public PhotosVo(int no, int postNo, String url, String regDate) {
		super();
		this.no = no;
		this.postNo = postNo;
		this.url = url;
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"url\":\"");
		builder.append(url);
		builder.append("\"}");
		return builder.toString();
	}

//	@Override
//	public String toString() {
//		return "{ \"url\":\"" + url + "\"}";
//	}
	
	
	
	
}
