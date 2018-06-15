package com.mystudy.chat_ano_webservices.config;

import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler {

	List<WebSocketSession> sessionsLive = new CopyOnWriteArrayList<>();
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception 
	{
		
		for(WebSocketSession sessionT : sessionsLive)
		{
		if(sessionT.isOpen()) {
			sessionT.sendMessage(new TextMessage(message.getPayload()));
		}
		}
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception 
	{
		
		System.out.println("connection establiashed session id " + session.getId());
		sessionsLive.add(session);	
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception 
	{
		System.out.println("connection closed " + session.getId() + " status : " + status);
		sessionsLive.remove(session);
		super.afterConnectionClosed(session, status);
	}
}
