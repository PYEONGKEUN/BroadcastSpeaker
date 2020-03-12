package com.poseungcar.broadcastspeaker.DTO;

public class GroupMember {
	Integer grpMemNo;
	Integer grpNo;
	String grpMemName;

	public Integer getGrpMemNo() {
		return grpMemNo;
	}



	public void setGrpMemNo(Integer grpMemNo) {
		this.grpMemNo = grpMemNo;
	}
	
	
	
	public Integer getGrpNo() {
		return grpNo;
	}



	public void setGrpNo(Integer grpNo) {
		this.grpNo = grpNo;
	}



	public String getGrpMemName() {
		return grpMemName;
	}



	public void setGrpMemName(String grpMemName) {
		this.grpMemName = grpMemName;
	}



	@Override
	public String toString() {
		return "GroupMember [grpMemNo=" + grpMemNo + ", grpNo=" + grpNo + ", grpMemName=" + grpMemName + "]";
	}

	
	




	



	
}
