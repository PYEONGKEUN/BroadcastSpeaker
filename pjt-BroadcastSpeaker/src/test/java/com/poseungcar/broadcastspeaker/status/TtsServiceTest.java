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
import com.poseungcar.broadcastspeaker.service.ITtsService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class})
public class TtsServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(TtsServiceTest.class);
	

	@Autowired
	ITtsService ttsService;


	
	@Test
	public void ttsServiceTest() throws Exception{

		ttsService.downloadMP3("테스트 입니다.", "skvudrms54");

	}
	

	
	
}
