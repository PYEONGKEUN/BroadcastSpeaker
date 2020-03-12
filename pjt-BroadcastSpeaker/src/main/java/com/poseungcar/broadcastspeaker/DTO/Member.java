package com.poseungcar.broadcastspeaker.DTO;

public class Member {

	String memId;
	String memEmail;
	Integer grpNo;
	String memPassword;
	String memName;
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public Integer getGrpNo() {
		return grpNo;
	}
	public void setGrpNo(Integer grpNo) {
		this.grpNo = grpNo;
	}
	public String getMemPassword() {
		return memPassword;
	}
	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	@Override
	public String toString() {
		return "Member [memId=" + memId + ", memEmail=" + memEmail + ", grpNo=" + grpNo + ", memPassword=" + memPassword
				+ ", memName=" + memName + "]";
	}
   
}
