package com.dms.dmsmoneyapi.excetionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
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

	private List<ErroDTO> criarListaErro(BindingResult bindingResult) {
		List<ErroDTO> erros = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String messageUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			erros.add(new ErroDTO(messageUser, fieldError.toString()));
		}
		return erros;
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String messageUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String messageDeveloper = ex.getCause().toString();
		List<ErroDTO> erros = Arrays.asList(new ErroDTO(messageUser, messageDeveloper));

		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<ErroDTO> erros = criarListaErro(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.NOT_ACCEPTABLE, request);
	}
}
