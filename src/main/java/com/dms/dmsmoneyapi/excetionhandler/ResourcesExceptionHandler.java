package com.dms.dmsmoneyapi.excetionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Classe que manipula as excessões da API
 * 
 * <pre>
 * <b>Nota:</b> Tenho um outro modelo deste no endereço:
 * <a href=
"https://github.com/diorgenesmorais/erp/blob/master/src/main/java/com/dms/erp/controller/handler/ResourcesExceptionHandler.java" target
="_blank" >GitHub</a>
 * </pre>
 * 
 * @author Diorgenes Morais
 * @version 1.0.0
 *
 */
@ControllerAdvice
public class ResourcesExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	/*
	 * Avoid NoSuchMessageException
	 */
	private String getMessageProperties(String code) {
		String userMessage;
		try{
			userMessage = messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
		}catch(NoSuchMessageException e) {
			userMessage = "Mensagem interna (NoSuchMessageException)";
		}
		return userMessage;
	}
	
	private List<ErrorDetails> criarListaErros(BindingResult bindingResult, HttpStatus status) {
		List<ErrorDetails> erros = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String messageUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			erros.add(ErrorDetailsBuilder.newBuilder()
					.title("Error")
					.status(status.value())
					.timestamp(new Date().getTime())
					.userMessage(messageUser)
					.developerMessage(fieldError.toString())
					.build());
		}
		return erros;
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String messageUser = getMessageProperties("mensagem.invalida");
		String messageDeveloper = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<ErrorDetails> erros = Arrays.asList(ErrorDetailsBuilder.newBuilder()
				.title("Error")
				.status(status.value())
				.timestamp(new Date().getTime())
				.userMessage(messageUser)
				.developerMessage(messageDeveloper)
				.build());

		return handleExceptionInternal(ex, erros, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		status = HttpStatus.NOT_ACCEPTABLE;
		List<ErrorDetails> erros = criarListaErros(ex.getBindingResult(), status);
		return handleExceptionInternal(ex, erros, headers, status, request);
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		String userMessage = getMessageProperties("resource.not-found");
		List<ErrorDetails> erros = Arrays.asList(ErrorDetailsBuilder.newBuilder()
				.title("Error")
				.status(HttpStatus.NOT_FOUND.value())
				.timestamp(new Date().getTime())
				.userMessage(userMessage)
				.developerMessage(ex.toString())
				.build());

		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, 
			WebRequest request){
		String userMessage = getMessageProperties("resource.not-acceptable");
		List<ErrorDetails> erros = Arrays.asList(ErrorDetailsBuilder.newBuilder()
				.title("Client Error")
				.status(HttpStatus.NOT_ACCEPTABLE.value())
				.timestamp(new Date().getTime())
				.userMessage(userMessage)
				.developerMessage(ExceptionUtils.getRootCauseMessage(ex))
				.build());

		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
	}

}
