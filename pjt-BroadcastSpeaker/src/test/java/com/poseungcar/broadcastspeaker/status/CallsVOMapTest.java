package com.poseungcar.broadcastspeaker.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.poseungcar.broadcastspeaker.VO.CallsVO;
import com.poseungcar.broadcastspeaker.config.RootConfig;
import com.poseungcar.broadcastspeaker.service.ICallsVoMapService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class})
public class CallsVOMapTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CallsVOMapTest.class);
	

	@Autowired
	ICallsVoMapService callsVoMapService;


	
//	@Test
//	public void CallsVoMapTest() throws Exception{
//		CallsVO callsVO = new CallsVO();
//		
//		callsVoMapService.offer("skvudrms54", "전체", "1234","hello1");
//		callsVoMapService.offer("skvudrms54", "전체", "1235","hello2");
//		callsVoMapService.offer("skvudrms54", "전체", "1236","hello3");
//		callsVoMapService.offer("skvudrms54", "전체", "1237","hello4");
//		callsVoMapService.offer("skvudrms54", "전체", "1238","hello5");
//		callsVoMapService.offer("npk", "고객대기실", "1238","hello5");	
//		LOGGER.info(CallsVoMap.userCallsVos.toString());		
//		LOGGER.info(callsVoMapService.poll("skvudrms54", "고객대기실").toString());
//		LOGGER.info(CallsVoMap.userCallsVos.toString());
//	}
//	

	
	
}
