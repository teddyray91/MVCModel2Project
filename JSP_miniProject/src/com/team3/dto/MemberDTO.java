package com.team3.dto;


public class MemberDTO {
	
	private int rowNum;
	private int memNum;
	private int aLive;
	private int super_num;
	private String memId;
	private String memPw;
	private String memEmail;
	private String memName;
	private String memSchool;
	private String memPart;
	private String memJob;
	private String memBirth;
	private int memGender;
	private String memPhone;
	private String memCreatedate;
	public MemberDTO() {
		super();
	}
	public MemberDTO(int rowNum, int memNum, int aLive, int super_num, String memId, String memPw, String memEmail, String memName,
			String memSchool, String memPart, String memJob, String memBirth, int memGender, String memPhone,
			String memCreatedate) {
		super();
		this.rowNum = rowNum;
		this.memNum = memNum;
		this.aLive = aLive;
		this.super_num = super_num;
		this.memId = memId;
		this.memPw = memPw;
		this.memEmail = memEmail;
		this.memName = memName;
		this.memSchool = memSchool;
		this.memPart = memPart;
		this.memJob = memJob;
		this.memBirth = memBirth;
		this.memGender = memGender;
		this.memPhone = memPhone;
		this.memCreatedate = memCreatedate;
	}
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	public int getaLive() {
		return aLive;
	}
	public void setaLive(int aLive) {
		this.aLive = aLive;
	}
	public int getSuper_num() {
		return super_num;
	}
	public void setSuper_num(int super_num) {
		this.super_num = super_num;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPw() {
		return memPw;
	}
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemSchool() {
		return memSchool;
	}
	public void setMemSchool(String memSchool) {
		this.memSchool = memSchool;
	}
	public String getMemPart() {
		return memPart;
	}
	public void setMemPart(String memPart) {
		this.memPart = memPart;
	}
	public String getMemJob() {
		return memJob;
	}
	public void setMemJob(String memJob) {
		this.memJob = memJob;
	}
	public String getMemBirth() {
		return memBirth;
	}
	public void setMemBirth(String memBirth) {
		this.memBirth = memBirth;
	}
	public int getMemGender() {
		return memGender;
	}
	public void setMemGender(int memGender) {
		this.memGender = memGender;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getMemCreatedate() {
		return memCreatedate;
	}
	public void setMemCreatedate(String memCreatedate) {
		this.memCreatedate = memCreatedate;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	
	
}