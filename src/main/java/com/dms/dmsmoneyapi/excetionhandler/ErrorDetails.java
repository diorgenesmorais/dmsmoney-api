package com.dms.dmsmoneyapi.excetionhandler;

/**
 * {@code ErrorDetails} class
 * 
 * @author Diorgenes Morais
 * @version 1.0.0
 *
 */
public class ErrorDetails {

	private String title;
	private int status;
	private long timestamp;
	private String userMessage;
	private String developerMessage;

	public ErrorDetails(String title, int status, long timestamp, String userMessage, String developerMessage) {
		this.title = title;
		this.status = status;
		this.timestamp = timestamp;
		this.userMessage = userMessage;
		this.developerMessage = developerMessage;
	}

	public String getTitle() {
		return title;
	}

	public int getStatus() {
		return status;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

}
