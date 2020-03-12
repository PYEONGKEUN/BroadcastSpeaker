package com.poseungcar.broadcastspeaker.DAO;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.poseungcar.broadcastspeaker.DTO.Audio;
import com.poseungcar.broadcastspeaker.config.RootConfig;
import com.poseungcar.broadcastspeaker.util.TimeLib;



/**
 * AudioDaoTest
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class})
public class AudioDAOTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AudioDAOTest.class);

    @Autowired
    private IAudioDAO AudioDAO;
    
    @Test
    public void selectTest(){
        Audio input = new Audio();
        List<Audio> audios = AudioDAO.select(input);

        for(Audio audio : audios){
            LOGGER.info(audio.toString());
        }
        
    }

    @Test
    public void updateTest(){
        Audio input = new Audio();
        input.setAudNo(1);
        input.setAudName("test");
        input.setGrpNo(1);
        AudioDAO.update(input);
    }

//    @Test
//    public void insertTest(){
//        Audio input = new Audio();
//        input.setAudDatetime(TimeLib.getCurrDateTime());
//        input.setAudName(TimeLib.getCurrDateTimeName());
//        input.setAudTgtName("전체");
//        input.setGrpNo(1);
//        
// 
//        AudioDAO.insert(input);
//    }
    
}