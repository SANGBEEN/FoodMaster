package kr.co.fm.vo;

import java.io.Serializable;

public class LikeVo implements Serializable{
	private String userId;
	private int rNo;
	private String isLike;
	private String regDate;
	
	public LikeVo() {
		super();
		
	}
	public LikeVo(String userId, int rNo, String isLike, String regDate) {
		super();
		this.userId = userId;
		this.rNo = rNo;
		this.isLike = isLike;
		this.regDate = regDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getrNo() {
		return rNo;
	}
	public void setrNo(int rNo) {
		this.rNo = rNo;
	}
	public String getIsLike() {
		return isLike;
	}
	public void setIsLike(String isLike) {
		this.isLike = isLike;
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
		builder.append("LikeVo [userId=");
		builder.append(userId);
		builder.append(", rNo=");
		builder.append(rNo);
		builder.append(", isLike=");
		builder.append(isLike);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append("]");
		return builder.toString();
	}
	
	
}

