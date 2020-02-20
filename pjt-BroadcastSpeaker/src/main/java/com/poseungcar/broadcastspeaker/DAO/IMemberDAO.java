package com.poseungcar.broadcastspeaker.DAO;

import java.util.List;

import com.poseungcar.broadcastspeaker.DTO.Member;




public interface IMemberDAO {

   
   
    public List<Member> select(Member member);

    
    public int insert(Member member);
    
    
    public int update(Member member);



    
}
