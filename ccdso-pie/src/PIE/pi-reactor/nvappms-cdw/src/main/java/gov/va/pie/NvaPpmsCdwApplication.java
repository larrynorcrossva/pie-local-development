package gov.va.pie;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = { "gov.va.*" })
@IntegrationComponentScan
@ImportResource("classpath:spring/si-components.xml")
@EnableScheduling
public class NvaPpmsCdwApplication {
	
	private static final Logger LOG = LogManager.getLogger(NvaPpmsCdwApplication.class);

	public static void main(String[] args) throws InterruptedException, IOException, SQLException {
		LOG.info("started app");
		SpringApplication.run(NvaPpmsCdwApplication.class, args);
	}

}
