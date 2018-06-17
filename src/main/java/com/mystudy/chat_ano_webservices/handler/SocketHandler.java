package com.mystudy.chat_ano_webservices.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.mystudy.chat_ano_webservices.pojo.UserMessagePOJO;
import com.mystudy.chat_ano_webservices.session.manager.HungrySessions;
import com.mystudy.chat_ano_webservices.session.manager.LiveSessions;
import com.mystudy.chat_ano_webservices.session.manager.PairSessions;
import com.mystudy.chat_ano_webservices.session.manager.SessionHandler;
import com.mystudy.chat_ano_webservices.session.manager.SessionUtilityCall;

@Component
public class SocketHandler extends TextWebSocketHandler {


	@Autowired
	SessionHandler sessionHandler;
	@Autowired 
	MessageHandler messageHandler;
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			messageHandler.sendMessage(session, message);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("connection establiashed session id " + session.getId());
		sessionHandler.afterConnectionEstablished(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("connection closed " + session.getId() + " status : " + status);
		sessionHandler.afterConnectionClosed(session);
		super.afterConnectionClosed(session, status);
	}
}
