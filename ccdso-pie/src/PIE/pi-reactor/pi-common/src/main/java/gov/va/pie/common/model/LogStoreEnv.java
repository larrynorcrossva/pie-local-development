package gov.va.pie.common.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "azure.log.store")
@Component
public  class LogStoreEnv {
	
	private String azureSharedKey;
	private String azureWid;
	private String appName;
	private String appVersion;
	
	private Boolean payload=true;

	public String getAzureSharedKey() {
		return azureSharedKey;
	}

	public void setAzureSharedKey(String azureSharedKey) {
		this.azureSharedKey = azureSharedKey;
	}

	public String getAzureWid() {
		return azureWid;
	}

	public void setAzureWid(String azureWid) {
		this.azureWid = azureWid;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public Boolean getPayload() {
		return payload;
	}

	public void setPayload(Boolean payload) {
		this.payload = payload;
	}
	
	
}
