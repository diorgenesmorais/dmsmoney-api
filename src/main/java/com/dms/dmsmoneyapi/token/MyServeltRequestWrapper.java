package com.dms.dmsmoneyapi.token;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;

class MyServeltRequestWrapper extends HttpServletRequestWrapper {

	private String refreshToken;

	public MyServeltRequestWrapper(HttpServletRequest request, String refreshToken) {
		super(request);
		this.refreshToken = refreshToken;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
		map.put("refresh_token", new String[] { this.refreshToken });
		map.setLocked(true);
		return map;
	}
}
