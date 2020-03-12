package com.poseungcar.broadcastspeaker.DAO;

import java.util.List;

import com.poseungcar.broadcastspeaker.DTO.Audio;
import com.poseungcar.broadcastspeaker.DTO.Member;




public interface IAudioDAO {

   
   
    public List<Audio> select(Audio audio);

    
    public int insert(Audio audio);
    
    
    public int update(Audio audio);



    
}
