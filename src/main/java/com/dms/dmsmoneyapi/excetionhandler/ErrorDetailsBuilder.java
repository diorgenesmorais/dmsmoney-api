package com.dms.dmsmoneyapi.excetionhandler;

/**
 * This {@code ErrorDetailsBuilder} class is a builder of {@code ErrorDetails}
 * class
 * 
 * @author Diorgenes Morais
 * @version 1.0.0
 *
 */
public class ErrorDetailsBuilder {

	private String title;
	private int status;
	private long timestamp;
	private String userMessage;
	private String developerMessage;

	private ErrorDetailsBuilder() {

	}

	/**
	 * Builder of this class
	 * 
	 * @return an {@code ErrorDetailsBuilder}
	 */
	public static ErrorDetailsBuilder newBuilder() {
		return new ErrorDetailsBuilder();
	}

	public ErrorDetailsBuilder title(String title) {
		this.title = title;
		return this;
	}

	public ErrorDetailsBuilder status(int status) {
		this.status = status;
		return this;
	}

	public ErrorDetailsBuilder timestamp(long timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public ErrorDetailsBuilder userMessage(String userMessage) {
		this.userMessage = userMessage;
		return this;
	}

	public ErrorDetailsBuilder developerMessage(String developerMessage) {
		this.developerMessage = developerMessage;
		return this;
	}

	/**
	 * Builder of {@code ErrorDetails}
	 * 
	 * @return instance of {@code ErrorDetails}
	 */
	public ErrorDetails build() {
		return new ErrorDetails(this.title, this.status, this.timestamp, this.userMessage, this.developerMessage);
	}
}
