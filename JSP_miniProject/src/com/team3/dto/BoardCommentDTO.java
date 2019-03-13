package com.team3.dto;

public class BoardCommentDTO {

	private int memNum;
	private int boardNum;
	private int commentNum;
	private String commentName;
	private String commentContent;
	private String commentRegDate;
	
	public BoardCommentDTO() { }
	
	public BoardCommentDTO(int memNum, int boardNum, int commentNum, String commentName, String commentContent, String commentRegDate) {
		super();
		this.memNum = memNum;
		this.boardNum = boardNum;
		this.commentNum = commentNum;
		this.commentName = commentName;
		this.commentContent = commentContent;
		this.commentRegDate = commentRegDate;
	}
	
	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	
	public String getCommentName() {
		return commentName;
	}

	public void setCommentName(String commentName) {
		this.commentName = commentName;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentRegDate() {
		return commentRegDate;
	}

	public void setCommentRegDate(String commentRegDate) {
		this.commentRegDate = commentRegDate;
	}

	@Override
	public String toString() {
		return "BoardCommentDTO [memNum=" + memNum + ", boardNum=" + boardNum + ", commentNum=" + commentNum
				+ ", commentName=" + commentName + ", commentContent=" + commentContent + ", commentRegDate="
				+ commentRegDate + "]";
	}

}
