package gov.va.pie;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import gov.va.pie.common.utils.CommonConstants;

@SpringBootApplication
@ComponentScan(basePackages = { "gov.va.*" })
@IntegrationComponentScan
@ImportResource("classpath:spring/si-components.xml")
@EnableScheduling
public class VistaPPmsMviApplication {

	private static final Logger LOG = LogManager.getLogger(VistaPPmsMviApplication.class);

	public static void main(String[] args) throws InterruptedException, IOException, SQLException {
		ConfigurableApplicationContext ctx = SpringApplication.run(VistaPPmsMviApplication.class, args);
		String tablePrefix = ctx.getEnvironment().getProperty("cdw.tables.prefix");
		if (!CommonConstants.DB_ENV.equals(tablePrefix)) {
			LOG.error("Data table prefix doesnt match with environment.");
			ctx.close();
		}

	}
}
