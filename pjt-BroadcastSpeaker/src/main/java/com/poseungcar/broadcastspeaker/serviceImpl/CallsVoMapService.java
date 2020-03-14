package com.poseungcar.broadcastspeaker.serviceImpl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.poseungcar.broadcastspeaker.VO.CallsVO;
import com.poseungcar.broadcastspeaker.controller.CekController;
import com.poseungcar.broadcastspeaker.service.ICallsVoMapService;
import com.poseungcar.broadcastspeaker.status.CallsVoMap;
@Service
public class CallsVoMapService implements ICallsVoMapService{

	private static final Logger logger = LoggerFactory.getLogger(CallsVoMapService.class);
	
	final private String[] PLACES = 
		{"전체","고객대기실","사무실","직원휴게실"};
	Map<String, CallsVO> callsVoMap = CallsVoMap.userCallsVos;
	// CallsVO를 쉽게 컨트롤 하기위한 메소드
	// CallsVO 객체와 
	@Override
	public void offer(String id, String place, String msg, String type, String fileName) throws Exception{
		// TODO Auto-generated method stub
		// 없을경우 생성 해당 $id : new CallsVO 생성
		if(!callsVoMap.containsKey(id)) {
			callsVoMap.put(id, new CallsVO());
		}		
		
		CallsVO callsVo = callsVoMap.get(id);
		if(place.equals(PLACES[0])) {
			//고객 대기실
			callsVo.offerCustomerWaitingRoom(msg, type, fileName);
			//직원휴게실
			callsVo.offerSeniorCommonRoom(msg, type, fileName);
			//사무실
			callsVo.offerOffice(msg, type, fileName);			
		}else if(place.equals(PLACES[1])) {				
			//고객 대기실
			callsVo.offerCustomerWaitingRoom(msg, type, fileName);
		}else if(place.equals(PLACES[2])) {
			//사무실
			callsVo.offerOffice(msg, type, fileName);
		}else if(place.equals(PLACES[3])) {
			//직원휴게실
			callsVo.offerSeniorCommonRoom(msg, type, fileName);
		}

		logger.info(callsVoMap.toString());

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
			//고객 대기실
			result = callsVo.pollCustomerWaitingRoom();
		}else if(place.equals(PLACES[2])) {
			//사무실
			result = callsVo.pollOffice();
		}else if(place.equals(PLACES[3])) {
			//직원휴게실
			result = callsVo.pollSeniorCommonRoom();
		}
		logger.info(result.toString());
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
			//고객 대기실
			result = callsVo.isEmptyCustomerWaitingRoom();
		}else if(place.equals(PLACES[2])) {
			//사무실
			result = callsVo.isEmptyOffice();
		}else if(place.equals(PLACES[3])) {
			//직원휴게실
			result = callsVo.isEmptySeniorCommonRoom();
		}
		
		
		return result;
	}



}
