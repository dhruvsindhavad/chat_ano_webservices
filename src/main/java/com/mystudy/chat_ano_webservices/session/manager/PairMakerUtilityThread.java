/**
 * 
 */
package com.mystudy.chat_ano_webservices.session.manager;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import com.mystudy.chat_ano_webservices.common.Constants;

/**
 * @author dhruvkumar
 *
 */

@Component
public class PairMakerUtilityThread implements Runnable {

	@Autowired
	private PairSessions pairSessions;
	@Autowired
	private HungrySessions hungrySessions;

	private WebSocketSession currentSession;

	public PairMakerUtilityThread() {
		// TODO Auto-generated constructor stub
	}

	public WebSocketSession getHungrySession() {
		return currentSession;
	}

	public void setHungrySession(WebSocketSession hungrySession) {
		this.currentSession = hungrySession;
	}

	public PairMakerUtilityThread(WebSocketSession hungrySession) {
		super();
		this.currentSession = hungrySession;
	}

	@Override
	public void run() {
	
			try {
				while (!match());
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

	boolean match() throws IOException, InterruptedException {
		if (hungrySessions.getHungrySessions().size() > 0) {
			for (WebSocketSession availableSession : hungrySessions.getHungrySessions()) {
				if ( availableSession != currentSession && availableSession.isOpen() && currentSession.isOpen()  ) {
					hungrySessions.getHungrySessions().remove(currentSession);
					hungrySessions.getHungrySessions().remove(availableSession);
					pairSessions.addPair(currentSession, availableSession);
					return true;
				}
			}
		}
		if(pairSessions.getPairSessions().containsKey(currentSession))
		{
			return true;
		}
		
		if(currentSession.isOpen()==false)
		{
			return true;
		}
		Thread.sleep(Constants.WAIT_FOR_PAIRING);
		return false;
	}
}
