package com.poseungcar.broadcastspeaker.util;

public class CekMsg {
	//CEK에서 TTS를 생성할때 사용하는 메시지 생성
	public static String callCarNumber(String number) {
			return number+"번 차량 수리완료되었습니다.";
	}
	public static String callGrpMemName(String name) {
		return name+"님 고객대기실로 와주세요.";
}
}
