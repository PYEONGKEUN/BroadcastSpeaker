package com.poseungcar.broadcastspeaker.DTO;

public class Audio {

	Integer audNo;
	Integer grpNo;
	String audName;
	String audDatetime;
	String audTgtName;
	String audMsg;
	
	
	
	public Integer getAudNo() {
		return audNo;
	}



	public void setAudNo(Integer audNo) {
		this.audNo = audNo;
	}



	public Integer getGrpNo() {
		return grpNo;
	}



	public void setGrpNo(Integer grpNo) {
		this.grpNo = grpNo;
	}



	public String getAudName() {
		return audName;
	}



	public void setAudName(String audName) {
		this.audName = audName;
	}



	public String getAudDatetime() {
		return audDatetime;
	}



	public void setAudDatetime(String audDatetime) {
		this.audDatetime = audDatetime;
	}



	public String getAudTgtName() {
		return audTgtName;
	}



	public void setAudTgtName(String audTgtName) {
		this.audTgtName = audTgtName;
	}



	public String getAudMsg() {
		return audMsg;
	}



	public void setAudMsg(String audMsg) {
		this.audMsg = audMsg;
	}



	@Override
	public String toString() {
		return "Audio [audNo=" + audNo + ", grpNo=" + grpNo + ", audName=" + audName + ", audDatetime=" + audDatetime
				+ ", audTgtName=" + audTgtName + ", audMsg=" + audMsg + "]";
	}
	
	
	
}
