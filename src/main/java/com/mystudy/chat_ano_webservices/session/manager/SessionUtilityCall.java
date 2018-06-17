package com.mystudy.chat_ano_webservices.session.manager;


import org.springframework.web.socket.WebSocketSession;

import com.mystudy.chat_ano_webservices.config.ApplicationContextHolder;

public class  SessionUtilityCall {

	public static void lookForStranger(WebSocketSession session)
	{
		
		PairMakerUtilityThread pairmakerUtilityThread = ApplicationContextHolder.getContext().getBean(PairMakerUtilityThread.class);
		pairmakerUtilityThread.setHungrySession(session);
		new Thread(pairmakerUtilityThread).start();
	}
}
