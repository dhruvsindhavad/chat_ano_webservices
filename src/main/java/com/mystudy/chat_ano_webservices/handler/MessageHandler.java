package com.mystudy.chat_ano_webservices.handler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;
import com.mystudy.chat_ano_webservices.common.Constants;
import com.mystudy.chat_ano_webservices.pojo.AbstractMessagePOJO;
import com.mystudy.chat_ano_webservices.pojo.SystemMessagePOJO;
import com.mystudy.chat_ano_webservices.pojo.UserMessagePOJO;
import com.mystudy.chat_ano_webservices.session.manager.PairSessions;

@Component
public class MessageHandler {

	@Autowired
	PairSessions pairSessions;
	Gson gson;

	public MessageHandler() {
		gson = new Gson();
	}

	public void sendMessage(WebSocketSession session, TextMessage message) throws IOException {
		AbstractMessagePOJO messagePOJO = new UserMessagePOJO(message.getPayload().toString());
		WebSocketSession partnerSession = pairSessions.getPairSessions().get(session);
		if (null != partnerSession && partnerSession.isOpen() == true) {
			partnerSession.sendMessage(new TextMessage(gson.toJson(messagePOJO)));
		}
	}
	
	public void notifyPartiesForConnection(WebSocketSession session1,WebSocketSession session2) throws IOException {
		
		if (null != session1 && session1.isOpen() == true) {
			session1.sendMessage(new TextMessage(gson.toJson(new SystemMessagePOJO(Constants.PARTNER_CONNECTION))));
		}
		if (null != session2 && session2.isOpen() == true) {
			session2.sendMessage(new TextMessage(gson.toJson(new SystemMessagePOJO(Constants.PARTNER_CONNECTION))));
		}
	}

	public void notifyPartnerForDisconnection(WebSocketSession partnerSession) throws IOException {
		if (null != partnerSession && partnerSession.isOpen() == true) {
			partnerSession
					.sendMessage(new TextMessage(gson.toJson(new SystemMessagePOJO(Constants.PARTNER_DISCONNECTION))));
		}
	}
}
