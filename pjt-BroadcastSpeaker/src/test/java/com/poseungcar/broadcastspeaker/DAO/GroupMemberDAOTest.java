package com.poseungcar.broadcastspeaker.DAO;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.poseungcar.broadcastspeaker.DTO.GroupMember;
import com.poseungcar.broadcastspeaker.config.RootConfig;



/**
 * GroupMemberDaoTest
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class})
public class GroupMemberDAOTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupMemberDAOTest.class);

    @Autowired
    private IGroupMemberDAO GroupMemberDAO;
    
    @Test
    public void selectTest(){
        GroupMember input = new GroupMember();
        List<GroupMember> groupGroupMember = GroupMemberDAO.select(input);

        for(GroupMember groupMember : groupGroupMember){
            LOGGER.info(groupMember.toString());
        }
        

    }

    @Test
    public void updateTest(){
        GroupMember input = new GroupMember();
        input.setGrpNo(1);
        input.setGrpMemName("Test");
        input.setGrpMemNo(1);
        GroupMemberDAO.update(input);
    }

//    @Test
//    public void insertTest(){
//        GroupMember input = new GroupMember();
//        input.setGrpMemName("Test");
//        input.setGrpNo(1);     
//        GroupMemberDAO.insert(input);
//    }
    
}