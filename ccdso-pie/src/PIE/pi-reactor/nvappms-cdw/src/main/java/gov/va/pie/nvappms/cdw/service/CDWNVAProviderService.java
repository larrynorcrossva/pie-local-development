package gov.va.pie.nvappms.cdw.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import gov.va.pie.common.utils.CommonConstants;
import gov.va.pie.nvappms.cdw.model.CDWProvider;
import gov.va.pie.nvappms.cdw.utils.CDWUtils;

@Service
public class CDWNVAProviderService {
	
	static final Logger LOG = LogManager.getLogger(CDWNVAProviderService.class);


	
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private DataSource dataSource;
	
	@Value("${cdw.nva.batchSize}")
	private int batchSize;

	private static final String PROCEDURE = "App." + CommonConstants.DB_ENV + "merge_NonVAProvider";
	
	@Transactional
	public void processCDWNVAProviders(List<CDWProvider> cdwProvidersList) throws PersistenceException, SQLException {
		
		LOG.info("***Total number of CDW Providers to Proccess: "+ cdwProvidersList.size() + " ***");
	
		Date startTime = new Date();
		
		List<List<CDWProvider>> nonVAProvidersToBeInsertedLists = Lists.partition(cdwProvidersList, batchSize);
		nonVAProvidersToBeInsertedLists.forEach(nonVAProvidersToBeInsertedSubList -> {
			StringBuffer insertStatementsToExecute = CDWUtils.determineTableToInsertEntity(CDWUtils.STAGING_NON_VA_PROVIDER_TABLE_NAME,
					CDWUtils.NON_VA_PROVIDER_COLUMNS);
			nonVAProvidersToBeInsertedSubList.forEach(cdwProvider -> {
				String insertStatement = CDWUtils.createInsertStatementForNonVAProvider(cdwProvider);
				if (!StringUtils.isEmpty(insertStatement)) {
					insertStatementsToExecute.append(insertStatement);
				}
			});
			if (insertStatementsToExecute.lastIndexOf(CDWUtils.VALUES) != -1) {
				//Add SQL "OPTION (RECOMPILE)"
				StringBuffer insertQuery = new StringBuffer(insertStatementsToExecute.toString().substring(0, insertStatementsToExecute.toString().lastIndexOf(",")));
				insertQuery.append(CDWUtils.BULK_LOAD_MECHANISM);
				CDWUtils.runProcedureInTransaction(entityManager, insertQuery.toString(), dataSource);
			}
		});
		

		
		Date endTime = new Date();
		long totalTimeTaken = TimeUnit.MILLISECONDS.toMinutes((endTime.getTime() - startTime.getTime()));
		
		LOG.info("*** Total Time to insert " + cdwProvidersList.size() + " provider is " + totalTimeTaken + " minutes.***");
		
	}
}
