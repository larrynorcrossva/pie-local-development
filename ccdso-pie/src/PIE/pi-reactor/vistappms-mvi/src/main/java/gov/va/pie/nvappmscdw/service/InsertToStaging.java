package gov.va.pie.nvappmscdw.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import gov.va.pie.nvappmscdw.utils.NvaUtils;

@Service
public class InsertToStaging {

	private static final Logger LOG = LogManager.getLogger(InsertToStaging.class);



	@Autowired
	private EntityManager entityManager;

	@Value("${vista.nva.individualInsertSize}")
	private int individualInsertSize;

	@Value("${vista.nva.insertCount}")
	private int insertCount;
	
	@Autowired
	private DataSource ds;

	@Transactional
	public <T> void popuplateStaging(List<T> dataList, Function<T, String> queryAssembler, String queryHeader,
			String logHeader)  throws PersistenceException, SQLException {

		Date startTime = new Date();

		List<List<T>> dataLists = Lists.partition(dataList, individualInsertSize);

		StringBuilder finalQuery = new StringBuilder("");
		int count = 0;

		int recordCountInOneBatch = individualInsertSize * insertCount;

		for (List<T> sublist : dataLists) {
			StringBuilder insertBuilder = new StringBuilder("");

			for (T service : sublist) {
				insertBuilder.append(queryAssembler.apply(service));
			}
			String insertQuery = queryHeader + " "
					+ insertBuilder.toString().substring(0, insertBuilder.toString().lastIndexOf(",")) + " ;";
			finalQuery.append(insertQuery);
			count++;

			if (count == insertCount) {
				Long time = new Date().getTime();
				try {
					NvaUtils.runProcedureInTransaction(entityManager, finalQuery.toString(), ds);
				} catch (Exception e) {
					LOG.error(finalQuery.toString());
					throw new SQLException(e.getMessage(), e);
				}
				LOG.info("Time took execute a query for " + logHeader + " " + recordCountInOneBatch + " records "
						+ (new Date().getTime() - time) / 1000 + " seconds");
				count = 0;
				finalQuery = new StringBuilder();
			}
		}

		if (!StringUtils.isEmpty(finalQuery.toString())) {
			NvaUtils.runProcedureInTransaction(entityManager, finalQuery.toString(), ds);
		}

		Date endTime = new Date();
		long totalTimeTaken = TimeUnit.MILLISECONDS.toMinutes((endTime.getTime() - startTime.getTime()));
		String logText = "*** Total Time to insert " + dataList.size() + " " + logHeader + " is " + totalTimeTaken
				+ " minutes.***";
		
		LOG.info(logText);

	}
}
