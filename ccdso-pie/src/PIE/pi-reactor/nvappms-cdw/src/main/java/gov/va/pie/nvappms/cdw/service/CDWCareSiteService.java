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

import gov.va.pie.nvappms.cdw.model.CDWCareSite;
import gov.va.pie.nvappms.cdw.utils.CDWUtils;

@Service
public class CDWCareSiteService {
	
	static final Logger LOG = LogManager.getLogger(CDWCareSiteService.class);
	
	
	

	@Autowired
	private EntityManager entityManager;
	
	@Value("${cdw.nva.batchSize}")
	private int batchSize;
	
	@Autowired
	private DataSource ds;
	
	@Transactional
	public void processCareSites(List<CDWCareSite> careSiteModelList) throws PersistenceException, SQLException {
		
		Date startTime = new Date();
		
		LOG.info("***Total number of CDW Care Sites to Proccess: "+ careSiteModelList.size() + " ***");
				
		List<List<CDWCareSite>> careSitesToBeInsertedLists = Lists.partition(careSiteModelList, batchSize);

		careSitesToBeInsertedLists.forEach(careSitesToBeInsertedSubList -> {
			StringBuffer insertStatementsToExecute = CDWUtils.determineTableToInsertEntity(CDWUtils.STAGING_CARE_SITE_TABLE_NAME,
					CDWUtils.CARE_SITE_COLUMNS);
			careSitesToBeInsertedSubList.forEach(cdwCareSite -> {
				String insertStatement = CDWUtils.createInsertStatementForCareSite(cdwCareSite);
				if (!StringUtils.isEmpty(insertStatement)) {
					insertStatementsToExecute.append(insertStatement);
				}
			});
			if (insertStatementsToExecute.lastIndexOf(CDWUtils.VALUES) != -1) {
				StringBuffer insertQuery = new StringBuffer(insertStatementsToExecute.toString().substring(0, insertStatementsToExecute.toString().lastIndexOf(",")));
				insertQuery.append(CDWUtils.BULK_LOAD_MECHANISM);
				CDWUtils.runProcedureInTransaction(entityManager, insertQuery.toString(), ds);
			}
		});	
		

		Date endTime = new Date();
		long totalTimeTaken = TimeUnit.MILLISECONDS.toMinutes((endTime.getTime() - startTime.getTime()));
		
		
		LOG.info(" *** Total Time to insert " + careSiteModelList.size() + " care sites is " + totalTimeTaken + " minutes.***");
	}
}
