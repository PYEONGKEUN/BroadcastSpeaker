package com.poseungcar.broadcastspeaker.DAO;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.poseungcar.broadcastspeaker.DTO.Member;
import com.poseungcar.broadcastspeaker.config.RootConfig;



/**
 * MemberDaoTest
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class})
public class MemberDAOTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberDAOTest.class);

    @Autowired
    private IMemberDAO MemberDAO;
    
    @Test
    public void selectTest(){
        Member input = new Member();
        input.setMemId("billip");
        input.setMemPassword("1234");
        input.setMemName("빌리빌리퐁");
        List<Member> members = MemberDAO.select(input);

        for(Member member : members){
            LOGGER.info(member.toString());
        }
        

    }

    @Test
    public void updateTest(){
        Member input = new Member();
        input.setMemId("billip");
        input.setMemPassword("1234");
        input.setMemName("빌리빌리퐁");
        MemberDAO.update(input);
    }

//    @Test
//    public void insertTest(){
//        Member input = new Member();
//        input.setMemId("billip3");
//        input.setMemPassword("1234");
//        input.setGrpNo(1);
//        input.setMemName("빌리빌리퐁");
//        input.setMemEmail("email");       
//        MemberDAO.insert(input);
//    }
    
}