package com.mystudy.chat_ano_webservices.session.manager;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

@Component
public class SessionHandler {

	@Autowired
	LiveSessions liveSessions;
	@Autowired
	HungrySessions hungrySessions;
	@Autowired
	PairSessions pairSessions;

	public void afterConnectionEstablished(WebSocketSession session) {
		liveSessions.addSession(session);
		hungrySessions.addSession(session);
		SessionUtilityCall.lookForStranger(session);
	}
	
	public void afterConnectionClosed(WebSocketSession session) throws IOException 
	{
		liveSessions.removeSession(session);
		hungrySessions.removeSession(session);
		pairSessions.removePair(session);
	}

}
