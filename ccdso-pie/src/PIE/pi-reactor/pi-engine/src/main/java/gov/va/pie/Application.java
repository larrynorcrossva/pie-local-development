package gov.va.pie;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import gov.va.pie.common.utils.CommonConstants;
//import gov.va.pie.service.VastService;

@SpringBootApplication
@ComponentScan(basePackages = { "gov.va.*" })
@IntegrationComponentScan
@ImportResource("classpath:spring/si-components.xml")
@EnableAsync
@EnableScheduling
public class Application {
	
	public static void main(String[] args) throws InterruptedException, IOException, SQLException {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
		String tablePrefix = ctx.getEnvironment().getProperty("cdw.tables.prefix");
		if(!CommonConstants.DB_ENV.equals(tablePrefix)) {
			ctx.close();
		}
		
	}
}
