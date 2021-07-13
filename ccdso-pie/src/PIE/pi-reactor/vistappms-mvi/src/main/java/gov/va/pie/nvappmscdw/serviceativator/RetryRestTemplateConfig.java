package gov.va.pie.nvappmscdw.serviceativator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RetryRestTemplateConfig {
	@Value("${vista.ppms.retry.backOffPeriod}")
	private long vistaPPMSRetryBackOffPeriod;
	
	@Value("${vista.ppms.retry.maxAttempt}")
	private int vistaPPMSRetryMaxAttemp;
	
	@Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
         
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(vistaPPMSRetryBackOffPeriod);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
 
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(vistaPPMSRetryMaxAttemp);
        retryTemplate.setRetryPolicy(retryPolicy);
         
        return retryTemplate;
    }
}
