package com.mystudy.chat_ano_webservices.session.manager;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import com.mystudy.chat_ano_webservices.handler.MessageHandler;
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PairSessions {

	private HashMap<WebSocketSession,WebSocketSession> sessionPairManager = new HashMap<>();
	@Autowired
	private MessageHandler messageHandler;
	
	public HashMap<WebSocketSession,WebSocketSession> getPairSessions()
	{
		return sessionPairManager;
	}
	
	void addPair(WebSocketSession session1, WebSocketSession session2) throws IOException
	{
		sessionPairManager.put(session1, session2);
		sessionPairManager.put(session2, session1);
		messageHandler.notifyPartiesForConnection(session1,session2);
	}
	
	WebSocketSession removePair(WebSocketSession session) throws IOException
	{
		WebSocketSession hungrySession = sessionPairManager.get(session);
		messageHandler.notifyPartnerForDisconnection(hungrySession);
		sessionPairManager.remove(session);
		sessionPairManager.remove(hungrySession);
		return hungrySession;
	}
	
	
	
	
}
