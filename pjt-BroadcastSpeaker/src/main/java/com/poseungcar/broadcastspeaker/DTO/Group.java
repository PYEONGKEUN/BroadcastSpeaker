package com.poseungcar.broadcastspeaker.DTO;

public class Group {

	Integer grpNo;
	String 	grpName;
	public Integer getGrNo() {
		return grpNo;
	}
	public void setGrpNo(Integer grpNo) {
		this.grpNo = grpNo;
	}
	public String getGrpName() {
		return grpName;
	}
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}
	@Override
	public String toString() {
		return "Group [grpNo=" + grpNo + ", grpName=" + grpName + "]";
	}


}
