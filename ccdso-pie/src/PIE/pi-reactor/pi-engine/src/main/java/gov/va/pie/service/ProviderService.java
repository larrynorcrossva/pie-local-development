package gov.va.pie.service;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import gov.va.pie.common.repositories.ProvidersRepository;



@Service
public class ProviderService {

	@Autowired
	private ProvidersRepository providersRepository;
	
	private static final Logger LOG = LogManager.getLogger(ProviderService.class);

	/**
	 * Updates Correlation Id for a given staff id.
	 * 
	 * @param staffId
	 *            - Cannot be zero
	 * @param ppmsCorrelationId
	 *            - Cannot be null, blank.
	 */
	@Transactional
	public boolean updateCorrlationId(int providerId, String ppmsCorrelationId) {

		if (providerId == 0 || StringUtils.isEmpty(ppmsCorrelationId))
			return false;
		providersRepository.updateCorrelationId(providerId, ppmsCorrelationId);
		return true;
	}
}
