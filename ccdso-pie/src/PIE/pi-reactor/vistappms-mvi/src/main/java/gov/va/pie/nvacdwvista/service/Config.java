package gov.va.pie.nvacdwvista.service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import gov.va.pie.nvacdwvista.hl7v3.MCCIIN000002UV01;

@Configuration
public class Config {

	JAXBContext jc = null;
	Unmarshaller vistaUnmarshaller = null;
	
	@Bean
	@Scope("prototype")
	public  Unmarshaller unmarshaller() throws JAXBException {
		if (vistaUnmarshaller == null) {
			JAXBContext jc = JAXBContext.newInstance(MCCIIN000002UV01.class);
			vistaUnmarshaller = jc.createUnmarshaller();
		}
		return vistaUnmarshaller;
	}

	@Bean
	public Jaxb2Marshaller marshaller() {
		
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("gov.va.pie.nvacdwvista.hl7v3");
		return marshaller;
	}

	@Bean
	@Scope("prototype")
	public SOAPConnector soapConnector() {
		SOAPConnector client = new SOAPConnector();
		client.setMarshaller(marshaller());
		client.setUnmarshaller(marshaller());
		return client;
	}

	@Bean
	@Scope("prototype")
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(1);
		pool.setMaxPoolSize(1);
		pool.setWaitForTasksToCompleteOnShutdown(true);
		return pool;
	}

}
