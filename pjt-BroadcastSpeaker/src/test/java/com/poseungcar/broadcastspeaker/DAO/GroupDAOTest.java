package com.poseungcar.broadcastspeaker.DAO;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.poseungcar.broadcastspeaker.DTO.Group;
import com.poseungcar.broadcastspeaker.config.RootConfig;



/**
 * GroupDaoTest
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class})
public class GroupDAOTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupDAOTest.class);

    @Autowired
    private IGroupDAO GroupDAO;
    
    @Test
    public void selectTest(){
        Group input = new Group();
        List<Group> groups = GroupDAO.select(input);

        for(Group group : groups){
            LOGGER.info(group.toString());
        }
        

    }

    @Test
    public void updateTest(){
        Group input = new Group();
        input.setGrpNo(1);
        input.setGrpName("Test");
        GroupDAO.update(input);
    }

//    @Test
//    public void insertTest(){
//        Group input = new Group();
//        input.setGrpName("test");
//        GroupDAO.insert(input);
//    }
    
}