package com.mystudy.chat_ano_webservices.session.manager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class HungrySessions {
List<WebSocketSession> sessionsHungry = new CopyOnWriteArrayList<>();
	
	public void addSession(WebSocketSession session)
	{
		sessionsHungry.add(session);
	}
	
	public void removeSession(WebSocketSession session)
	{
		sessionsHungry.remove(session);
	}
	
	public List<WebSocketSession> getHungrySessions()
	{
		return sessionsHungry;
	}
}
