package gov.va.pie.nvappms.cdw.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import gov.va.pie.nvappms.cdw.model.CDWAgreement;
import gov.va.pie.nvappms.cdw.utils.CDWUtils;


@Service
public class CDWAgreementService {
	
	static final Logger LOG = LogManager.getLogger(CDWAgreementService.class);


	@Autowired
	private EntityManager entityManager;

	@Value("${cdw.nva.batchSize}")
	private int batchSize;
	
	@Autowired
	private DataSource ds;
	
	@Transactional
	public void processAgreements(List<CDWAgreement> agreementList) throws PersistenceException, SQLException {
		
		Date startTime = new Date();
		
		LOG.info("***Total number of CDW Agreements to Proccess: "+ agreementList.size() + " ***");
		
		List<List<CDWAgreement>> providerAgreementToBeInsertedLists = Lists.partition(agreementList, batchSize);
		
		providerAgreementToBeInsertedLists.forEach(providerAgreementToBeInsertedSubList -> {
			StringBuffer insertStatementsToExecute = CDWUtils.determineTableToInsertEntity(CDWUtils.STAGING_PROVIDER_AGREEMENT_TABLE_NAME,
					CDWUtils.AGREEMENT_COLUMNS);
			providerAgreementToBeInsertedSubList.forEach(cdwAgreement -> {
				String insertStatement = CDWUtils.createInsertStatementForAgreements(cdwAgreement);
				insertStatementsToExecute.append(insertStatement);
			});
			if (insertStatementsToExecute.lastIndexOf(CDWUtils.VALUES) != -1) {
				StringBuffer insertQuery = new StringBuffer(insertStatementsToExecute.toString().substring(0, insertStatementsToExecute.toString().lastIndexOf(",")));
				insertQuery.append(CDWUtils.BULK_LOAD_MECHANISM);	
				CDWUtils.runProcedureInTransaction(entityManager, insertQuery.toString(), ds);
			}
		});
		
		Date endTime = new Date();
		long totalTimeTaken = TimeUnit.MILLISECONDS.toMinutes((endTime.getTime() - startTime.getTime()));
		
		LOG.info("Total Time to insert " + providerAgreementToBeInsertedLists.size() + " provider agreement is " + totalTimeTaken + " minutes.***");
	}
}