package gov.va.pie.cdw.model;

import java.util.List;

import com.thoughtworks.xstream.XStream;

public class VAProviders {

	public List<VAProvider> providers;

	/*
	 * Intentionally blank
	 */
	public VAProviders() {

	}

	public List<VAProvider> getProviders() {
		return providers;
	}

	public void setProviders(List<VAProvider> providers) {
		this.providers = providers;
	}

	public String toXMLString() {
		XStream xs = new XStream();
		xs.alias("providers", VAProviders.class);
		xs.addImplicitCollection(VAProviders.class, "providers");
		xs.alias("provider", VAProvider.class);
		return xs.toXML(this);
	}

	/**
	 * Convert from XML to object
	 * 
	 * @param xml
	 * @return
	 */
	public static Object fromXMLToJava(String xml) {
		XStream xs = new XStream();
		xs.alias("providers", VAProviders.class);
		xs.addImplicitCollection(VAProviders.class, "providers");
		xs.alias("provider", VAProvider.class);
		return xs.fromXML(xml);
	}
}

