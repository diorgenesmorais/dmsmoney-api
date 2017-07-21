package com.dms.dmsmoneyapi.exceptionhandler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.dms.dmsmoneyapi.excetionhandler.ErrorDetails;
import com.dms.dmsmoneyapi.excetionhandler.ErrorDetailsBuilder;

public class ErrorDetailsTest {

	private ErrorDetails error;
	
	@Test
	public void deveCriarUmObjetoErrorDetailsDefinindoTitle() {
		String expected = "Testando";
		error = ErrorDetailsBuilder.newBuilder()
									.title("Testando")
									.build();

		assertEquals(expected, error.getTitle());
	}
	
	public void deveCriarUmErrorDetailsDefinindoStatus(){
		int expected = 404;
		error = ErrorDetailsBuilder.newBuilder()
									.status(404)
									.build();

		assertEquals(expected, error.getStatus());
	}
}
