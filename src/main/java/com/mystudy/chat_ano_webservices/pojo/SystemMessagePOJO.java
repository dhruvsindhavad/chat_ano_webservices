package com.mystudy.chat_ano_webservices.pojo;


import com.mystudy.chat_ano_webservices.common.Constants;

//@Component
public class SystemMessagePOJO extends AbstractMessagePOJO {
	
	public SystemMessagePOJO(String message) {
		this.setFrom(Constants.SYSTEM);
		this.setMessage(message);
	}
}
