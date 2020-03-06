package com.poseungcar.broadcastspeaker.serviceImpl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.poseungcar.broadcastspeaker.VO.CallsVO;
import com.poseungcar.broadcastspeaker.service.ICallsVoMapService;
import com.poseungcar.broadcastspeaker.status.CallsVoMap;
@Service
public class CallsVoMapService implements ICallsVoMapService{

	final private String[] PLACES = 
		{"전체","고객대기실","프론트","사무실","휴게실","정비실","사장실","창구","직원휴게실"};
	Map<String, CallsVO> callsVoMap = CallsVoMap.userCallsVos;
	// CallsVO를 쉽게 컨트롤 하기위한 메소드
	// CallsVO 객체와 
	@Override
	public void offer(String id, String place, String number,String fileName) throws Exception{
		// TODO Auto-generated method stub
		// 없을경우 생성 해당 $id : new CallsVO 생성
		if(!callsVoMap.containsKey(id)) {
			callsVoMap.put(id, new CallsVO());
		}		
		
		CallsVO callsVo = callsVoMap.get(id);
		if(place.equals(PLACES[0])) {
			//고객 대기실
			callsVo.offerCustomerWaitingRoom(number,fileName);
			//프론트
			callsVo.offerFront(number,fileName);
			//사무실
			callsVo.offerOffice(number,fileName);
			//휴게실
			callsVo.offerRestArea(number,fileName);
			//정비실
			callsVo.offerMaintenanceRoom(number,fileName);
			//사장실
			callsVo.offerPresidentOffice(number,fileName);
			//창구
			callsVo.offerWindow(number,fileName);

		}else if(place.equals(PLACES[1])) {				
			//고객 대기실
			callsVo.offerCustomerWaitingRoom(number,fileName);
		}else if(place.equals(PLACES[2])) {
			//프론트
			callsVo.offerFront(number,fileName);
		}else if(place.equals(PLACES[3])) {
			//사무실
			callsVo.offerOffice(number,fileName);
		}else if(place.equals(PLACES[4])) {
			//휴게실
			callsVo.offerRestArea(number,fileName);
		}else if(place.equals(PLACES[5])) {
			//정비실
			callsVo.offerMaintenanceRoom(number,fileName);
		}else if(place.equals(PLACES[6])) {
			//사장실
			callsVo.offerPresidentOffice(number,fileName);
		}else if(place.equals(PLACES[7])) {
			//창구
			callsVo.offerWindow(number,fileName);
		}else if(place.equals(PLACES[8])) {
			//직원휴게실
			callsVo.offerSeniorCommonRoom(number,fileName);
		}



	}

	@Override
	public Map<String, String> poll(String id, String place) throws Exception{
		// TODO Auto-generated method stub
		Map<String, String> result = null;
		if(!callsVoMap.containsKey(id)) {
			callsVoMap.put(id, new CallsVO());
		}		
		
		CallsVO callsVo = callsVoMap.get(id);
		
		if(place.equals(PLACES[1])) {
			result = callsVo.pollCustomerWaitingRoom();
		}else if(place.equals(PLACES[2])) {
			result = callsVo.pollFront();
		}else if(place.equals(PLACES[3])) {
			result = callsVo.pollOffice();
		}else if(place.equals(PLACES[4])) {
			result = callsVo.pollRestArea();
		}else if(place.equals(PLACES[5])) {
			result = callsVo.pollMaintenanceRoom();
		}else if(place.equals(PLACES[6])) {
			result = callsVo.pollPresidentOffice();
		}else if(place.equals(PLACES[7])) {
			result = callsVo.pollWindow();
		}else if(place.equals(PLACES[8])) {
			//직원휴게실
			result = callsVo.pollSeniorCommonRoom();
		}
		return result;
	}

	@Override
	public boolean isEmpty(String id, String place) throws Exception{
		// TODO Auto-generated method stub
		boolean result = false;
		
		if(!callsVoMap.containsKey(id)) {
			callsVoMap.put(id, new CallsVO());
		}		
		
		CallsVO callsVo = callsVoMap.get(id);
		
		if(place.equals(PLACES[1])) {
			result = callsVo.isEmptyCustomerWaitingRoom();
		}else if(place.equals(PLACES[2])) {
			result = callsVo.isEmptyFront();
		}else if(place.equals(PLACES[3])) {
			result = callsVo.isEmptyOffice();
		}else if(place.equals(PLACES[4])) {
			result = callsVo.isEmptyRestArea();
		}else if(place.equals(PLACES[5])) {
			result = callsVo.isEmptyMaintenanceRoom();
		}else if(place.equals(PLACES[6])) {
			result = callsVo.isEmptyPresidentOffice();
		}else if(place.equals(PLACES[7])) {
			result = callsVo.isEmptyWindow();
		}else if(place.equals(PLACES[8])) {
			//직원휴게실
			callsVo.isEmptySeniorCommonRoom();
		}
		return result;
	}



}