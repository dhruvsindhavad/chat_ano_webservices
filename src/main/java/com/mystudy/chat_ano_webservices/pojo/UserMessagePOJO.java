package com.mystudy.chat_ano_webservices.pojo;

import org.springframework.stereotype.Component;

import com.mystudy.chat_ano_webservices.common.Constants;

//@Component
public class UserMessagePOJO extends AbstractMessagePOJO {

	public UserMessagePOJO(String message) {
		this.setFrom(Constants.USER);
		this.setMessage(message);
	}
}
