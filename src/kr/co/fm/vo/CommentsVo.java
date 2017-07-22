package kr.co.fm.vo;

import java.io.Serializable;

public class CommentsVo implements Serializable{
	private int no;
	private int postNo;
	private int rNo;
	private String userId;
	private String content;
	private int parentNo;
	private String regDate;
	
	public CommentsVo() {
		super();
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

	
	public int getrNo() {
		return rNo;
	}

	public void setrNo(int rNo) {
		this.rNo = rNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getParentNo() {
		return parentNo;
	}

	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
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
		builder.append("{\"no\":");
		builder.append("\""+no+"\"");
		builder.append(", \"postno\":");
		builder.append("\""+postNo+"\"");
		builder.append(","+"rno"+":");
		builder.append("\""+rNo+"\"");
		builder.append(", \"userid\":");
		builder.append("'"+userId+"'");
		builder.append(", \"content\":");
		builder.append("\""+content+"\"");
		builder.append(", \"parentno\":");
		builder.append("'"+parentNo+"'");
		builder.append(", \"regdate\":");
		builder.append("'"+regDate+"'");
		builder.append("}");
		return builder.toString();
	}
	
}
