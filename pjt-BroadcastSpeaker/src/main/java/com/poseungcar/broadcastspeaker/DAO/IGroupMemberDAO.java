package com.poseungcar.broadcastspeaker.DAO;

import java.util.List;

import com.poseungcar.broadcastspeaker.DTO.GroupMember;
import com.poseungcar.broadcastspeaker.DTO.Member;




public interface IGroupMemberDAO {

   
   
    public List<GroupMember> select(GroupMember groupMember);

    
    public int insert(GroupMember groupMember);
    
    
    public int update(GroupMember groupMember);



    
}
