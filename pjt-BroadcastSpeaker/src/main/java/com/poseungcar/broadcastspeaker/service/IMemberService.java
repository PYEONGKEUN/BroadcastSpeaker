package com.poseungcar.broadcastspeaker.service;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseungcar.broadcastspeaker.DTO.Member;

/**
 * ITopNavService
 */
public interface IMemberService {
    public boolean login(Member member, HttpSession session);
    public boolean logout(HttpSession session);
    public boolean isLogin(HttpSession session);
    public boolean idIsExist(Member member);
    
    //for android hybrid app
    public boolean loginCookie(Member member, Cookie cookie);
}
