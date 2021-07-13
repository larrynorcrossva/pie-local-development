package gov.va.pie.nvacdw.scheduler;

public class AadToken {
	
	private String access_token;
	private String expires_on;
	private String resource;
	private String token_type;
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getExpires_on() {
		return expires_on;
	}
	public void setExpires_on(String expires_on) {
		this.expires_on = expires_on;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	@Override
	public String toString() {
		return "AadToken [access_token=" + access_token + ", expires_on=" + expires_on + ", resource=" + resource
				+ ", token_type=" + token_type + "]";
	}
}
