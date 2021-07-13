package gov.va.pie.nvappms.cdw.serviceactivator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RetryRestTemplateConfig {
	
	@Value("${cdw.ppms.retry.backOffPeriod}")
	private long cdwPPMSRetryBackOffPeriod;
	
	@Value("${cdw.ppms.retry.maxAttempt}")
	private int cdwPPMSRetryMaxAttemp;
	
	@Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
         
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(cdwPPMSRetryBackOffPeriod);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
 
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(cdwPPMSRetryMaxAttemp);
        retryTemplate.setRetryPolicy(retryPolicy);
         
        return retryTemplate;
    }
}
