package com.poseungcar.broadcastspeaker.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poseungcar.broadcastspeaker.DAO.IAudioDAO;
import com.poseungcar.broadcastspeaker.DAO.IMemberDAO;
import com.poseungcar.broadcastspeaker.DTO.Audio;
import com.poseungcar.broadcastspeaker.DTO.Member;
import com.poseungcar.broadcastspeaker.service.ICallsVoMapService;
import com.poseungcar.broadcastspeaker.service.IClientService;
import com.poseungcar.broadcastspeaker.service.ITtsService;
import com.poseungcar.broadcastspeaker.util.CPVMsg;
import com.poseungcar.broadcastspeaker.util.TimeLib;


@Service
public class ClientService implements IClientService{

	private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
	
	@Autowired
	ICallsVoMapService callsVoMapService;

	@Autowired
	ITtsService ttsService;

	@Autowired
	IAudioDAO audioDAO;
	
	@Autowired
	IMemberDAO memberDAO;



	@Override
	public boolean callCarNumber(String id, String place, String number) {
		logger.info("-----callCarNumber start-----");
		// TODO Auto-generated method stub
		try {
			String msg = CPVMsg.callCarNumber(number);
			String fileName;
			
			//			
			Audio findAudio = new Audio();
			findAudio.setAudMsg(msg);
			List<Audio> findAudios = audioDAO.select(findAudio);


			//결과가 있을때 없을때 구분		
			if(findAudios.isEmpty()) {
				
				if((fileName = ttsService.downloadMP3(msg,id)) == null) 
					return false;
				
			}else {
				fileName = findAudios.get(0).getAudName();
			}
			//Aduio 테이블에 새로운행 추가
			Audio insertAudio = new Audio();
			insertAudio.setAudDatetime(TimeLib.getCurrDateTime());
			insertAudio.setAudMsg(msg);
			insertAudio.setAudName(fileName);			
			//insertAudio.setAudNo(audNo);//pk는 자동생성
			insertAudio.setAudTgtName(place);
			
			
			
			//insertAudio.setGrpNo(Integer); 하기위해 호원의 grpNo 가져와서 Audio에 데이터 삽입
			Member findMember = new Member();
			findMember.setMemId(id);
			List<Member> findedMember = memberDAO.select(findMember);
			
			if(!findedMember.isEmpty()) {
				insertAudio.setGrpNo(findedMember.get(0).getGrpNo());
			}			
				
			audioDAO.insert(insertAudio);
			logger.info(insertAudio.toString());
			
			callsVoMapService.offer(id, place, number,"number", fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			for(StackTraceElement element : e.getStackTrace()) {
				logger.error(element.toString());
			}
			return false;
		}

		logger.info("-----callCarNumber End-----");
		return true;
	}

	@Override
	public boolean callGrpMemName(String id, String place, String grpMemName) {
		// TODO Auto-generated method stub
		logger.info("-----callGrpMemName start-----");
		
		try {
			String msg = CPVMsg.callGrpMemName(grpMemName);
			String fileName;
			
			//			
			Audio findAudio = new Audio();
			findAudio.setAudMsg(msg);
			List<Audio> findAudios = audioDAO.select(findAudio);
			

			//결과가 있을때 없을때 구분		
			if(findAudios.isEmpty()) {			
				if((fileName = ttsService.downloadMP3(msg,id)) == null) 
					return false;		
			}else {
				fileName = findAudios.get(0).getAudName();
			}
			logger.info(fileName);
			//Aduio 테이블에 새로운행 추가
			Audio insertAudio = new Audio();
			insertAudio.setAudDatetime(TimeLib.getCurrDateTime());
			insertAudio.setAudMsg(msg);
			insertAudio.setAudName(fileName);			
			//insertAudio.setAudNo(audNo);//pk는 자동생성
			insertAudio.setAudTgtName(place);
			
			
			//insertAudio.setGrpNo(Integer); 하기위해 호원의 grpNo 가져와서 Audio에 데이터 삽입
			Member findMember = new Member();
			findMember.setMemId(id);
			List<Member> findedMember = memberDAO.select(findMember);
			
			if(!findedMember.isEmpty()) {
				insertAudio.setGrpNo(findedMember.get(0).getGrpNo());
			}		
			
			audioDAO.insert(insertAudio);
			logger.info(insertAudio.toString());
			
			
			callsVoMapService.offer(id, place, grpMemName, "grpMemName",fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			for(StackTraceElement element : e.getStackTrace()) {
				logger.error(element.toString());
			}
			return false;
		}

		logger.info("-----callGrpMemName End-----");
		return true;
	}



}
