package com.poseungcar.broadcastspeaker.DAO;

import java.util.List;

import com.poseungcar.broadcastspeaker.DTO.Group;





public interface IGroupDAO {

   
   
    public List<Group> select(Group group);

    
    public int insert(Group group);
    
    
    public int update(Group group);



    
}
