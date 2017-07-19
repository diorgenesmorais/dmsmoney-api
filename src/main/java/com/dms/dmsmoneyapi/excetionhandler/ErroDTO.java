package com.dms.dmsmoneyapi.excetionhandler;

/**
 * 
 * @author Diorgenes Morais
 *
 */
public class ErroDTO {

	private String messageUser;
	private String messageDeveloper;

	public ErroDTO(String messageUser, String messageDeveloper) {
		this.messageUser = messageUser;
		this.messageDeveloper = messageDeveloper;
	}

	public String getMessageUser() {
		return messageUser;
	}

	public String getMessageDeveloper() {
		return messageDeveloper;
	}

}
