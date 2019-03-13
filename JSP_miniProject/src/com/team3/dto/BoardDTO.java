package com.team3.dto;

public class BoardDTO {
	
	private int rowNum;
	private int memNum;
	private int boardNum;
	private String boardSubject;
	private String boardName;
	private String boardContent;
	private String boardRegDate;
	private int boardLike, boardHate;
	private int boardViewCount;
	private String boardCategory;
	
	public BoardDTO() { }
	
	public BoardDTO(int rowNum, int memNum, int boardNum, String boardSubject, String boardName, String boardContent,
			String boardRegDate, int boardLike, int boardHate, int boardViewCount, String boardCategory) {
		this.rowNum = rowNum;
		this.memNum = memNum;
		this.boardNum = boardNum;
		this.boardSubject = boardSubject;
		this.boardName = boardName;
		this.boardContent = boardContent;
		this.boardRegDate = boardRegDate;
		this.boardLike = boardLike;
		this.boardHate = boardHate;
		this.boardViewCount = boardViewCount;
		this.boardCategory = boardCategory;
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

	public String getBoardSubject() {
		return boardSubject;
	}

	public void setBoardSubject(String boardSubject) {
		this.boardSubject = boardSubject;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardRegDate() {
		return boardRegDate;
	}

	public void setBoardRegDate(String boardRegDate) {
		this.boardRegDate = boardRegDate;
	}

	public int getBoardLike() {
		return boardLike;
	}

	public void setBoardLike(int boardLike) {
		this.boardLike = boardLike;
	}

	public int getBoardHate() {
		return boardHate;
	}

	public void setBoardHate(int boardHate) {
		this.boardHate = boardHate;
	}

	public int getBoardViewCount() {
		return boardViewCount;
	}

	public void setBoardViewCount(int boardViewCount) {
		this.boardViewCount = boardViewCount;
	}

	public String getBoardCategory() {
		return boardCategory;
	}

	public void setBoardCategory(String boardCategory) {
		this.boardCategory = boardCategory;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

}
