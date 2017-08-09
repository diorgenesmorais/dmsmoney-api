package com.dms.dmsmoneyapi.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("dmsmoney")
public class DmsmoneyApiProperty {

	private String originAllow = "http://localhost:8000";
	private final Security security = new Security();

	public String getOriginAllow() {
		return originAllow;
	}

	public void setOriginAllow(String originAllow) {
		this.originAllow = originAllow;
	}

	public Security getSecurity() {
		return security;
	}

	public static class Security {
		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
	}
}
