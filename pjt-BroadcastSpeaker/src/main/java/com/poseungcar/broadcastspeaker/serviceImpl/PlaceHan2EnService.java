package com.poseungcar.broadcastspeaker.serviceImpl;

import org.springframework.stereotype.Service;

import com.poseungcar.broadcastspeaker.VO.CallsVO;
import com.poseungcar.broadcastspeaker.service.IPlaceHan2EnService;
@Service
public class PlaceHan2EnService implements IPlaceHan2EnService{

	final private String[] PLACES = {"고객대기실","프론트","사무실","휴게실","정비실","사장실","창구"};
	
	// CallsVO를 쉽게 컨트롤 하기위한 메소드
	// CallsVO 객체와 
	@Override
	public void offer(CallsVO callsVo,String place, String mpUrl) throws Exception{
		// TODO Auto-generated method stub
		
		if(place.equals(PLACES[0])) {
			//고객 대기실
			callsVo.offerCustomerWaitingRoom(mpUrl);
		}else if(place.equals(PLACES[1])) {
			//프론트
			callsVo.offerfront(mpUrl);
		}else if(place.equals(PLACES[2])) {
			//사무실
			callsVo.offeroffice(mpUrl);
		}else if(place.equals(PLACES[3])) {
			//휴게실
			callsVo.offerrestArea(mpUrl);
		}else if(place.equals(PLACES[4])) {
			//정비실
			callsVo.offermaintenanceRoom(mpUrl);
		}else if(place.equals(PLACES[5])) {
			//사장실
			callsVo.offerpresidentOffice(mpUrl);
		}else if(place.equals(PLACES[6])) {
			//창구
			callsVo.offerwindow(mpUrl);
		}
	}

	@Override
	public String poll(CallsVO callsVo, String place) throws Exception{
		// TODO Auto-generated method stub
		String result = "";
		if(place.equals(PLACES[0])) {
			result = callsVo.pollCustomerWaitingRoom();
		}else if(place.equals(PLACES[1])) {
			result = callsVo.pollfront();
		}else if(place.equals(PLACES[2])) {
			result = callsVo.polloffice();
		}else if(place.equals(PLACES[3])) {
			result = callsVo.pollrestArea();
		}else if(place.equals(PLACES[4])) {
			result = callsVo.pollmaintenanceRoom();
		}else if(place.equals(PLACES[5])) {
			result = callsVo.pollpresidentOffice();
		}else if(place.equals(PLACES[6])) {
			result = callsVo.pollwindow();
		}
		return result;
	}

	@Override
	public boolean isEmpty(CallsVO callsVo, String place) throws Exception{
		// TODO Auto-generated method stub
		boolean result = false;
		if(place.equals(PLACES[0])) {
			result = callsVo.isEmptyCustomerWaitingRoom();
		}else if(place.equals(PLACES[1])) {
			result = callsVo.isEmptyfront();
		}else if(place.equals(PLACES[2])) {
			result = callsVo.isEmptyoffice();
		}else if(place.equals(PLACES[3])) {
			result = callsVo.isEmptyrestArea();
		}else if(place.equals(PLACES[4])) {
			result = callsVo.isEmptymaintenanceRoom();
		}else if(place.equals(PLACES[5])) {
			result = callsVo.isEmptypresidentOffice();
		}else if(place.equals(PLACES[6])) {
			result = callsVo.isEmptywindow();
		}
		return result;
	}

}
