package gov.va.pie.nvacdwvista.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import gov.va.pie.common.utils.CommonConstants;
import gov.va.pie.nvacdwvista.util.BeanUtil;
import gov.va.pie.nvacdwvista.util.SoapConstants;

@Component
public class DataFetcher {

	private static final Logger LOG = LogManager.getLogger(DataFetcher.class);



	@Autowired
	private EntityManagerFactory emf;

	@Autowired
	private EntityManager entityManager;

	// @Autowired
	// private Soap1301ClientService soap1301ClientService;

	// @Autowired
	// private Soap1302ClientService soap1302ClientService;

	@Value("${vista.mvi.batchSize}")
	private String batchSize;

	@Autowired
	private MviResponseProcessor mviResponseProcessor;

	@Value("${cdw.nva.maxcalls}")
	private int maxcalls;

	@Value("${vista.mvi.call1302First}")
	private boolean call1302First;

	@Value("${vista.mvi.populateNewSeeding}")
	private boolean populateNewSeeding;

	private int mviCallCount = 0;

	private Long time;

	@Autowired
	private DataSource ds;

	@ServiceActivator
	public void getData(Message<?> responseMessage) {

		// Processing new providers
		mviCallCount = 0;
		time = new Date().getTime();

		if (populateNewSeeding) {
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			StoredProcedureQuery storedProcedureQuery = em
					.createStoredProcedureQuery("App." + CommonConstants.DB_ENV + "CalculatePastSeededProviders");
			storedProcedureQuery.executeUpdate();
			tx.commit();
			em.close();
			if (ds != null) {
				LOG.info("After calling CalculatePastSeededProviders CommonUtils Active: " + ds.getNumActive() + " "
						+ "Idle: " + ds.getNumIdle());
			}

			LOG.info("Done processing " + "App." + CommonConstants.DB_ENV + "CalculatePastSeededProviders");
		}
		if (call1302First) {
			call1302();
			call1301();
		} else {
			call1301();
			call1302();
		}

	}

	public void call1301() {
		int count1301 = 0;

		

		mviResponseProcessor.setMode(SoapConstants.NEW);
		
		LOG.info("Fectching new providers");
		//executeIsRetrieved();
		// ThreadPoolTaskExecutor executor1301 = (ThreadPoolTaskExecutor)
		// BeanUtil.getBean(ThreadPoolTaskExecutor.class);
		mviResponseProcessor.getResponseList().clear();

		while (getNewProvidersCount() > 0) {
			
			String ids = getNewProviderIds();

			long queryTime = new java.util.Date().getTime();

			@SuppressWarnings("unchecked")
			List<Object[]> nonVaProviders = entityManager.createNativeQuery(" Select distinct * From APP."
					+ CommonConstants.DB_ENV + "NewVistaProviders NVA where NonVaprovider_id in (" + ids
					+ ") order by NonVaprovider_id ,StationNumber ,InactiveFlag desc, SpecialityCode, DeaNumber")
					.getResultList();

			LOG.info(" time for querying data : " + (new java.util.Date().getTime() - queryTime) / 1000);

			int recordsCount = get1301Count(ids);

			if (CollectionUtils.isNotEmpty(nonVaProviders)) {
				count1301 = count1301 + recordsCount;
				Soap1301ClientService soap1301ClientService = (Soap1301ClientService) BeanUtil
						.getBean(Soap1301ClientService.class);
				soap1301ClientService.setResults(nonVaProviders);
				soap1301ClientService.setCount(mviCallCount);

				LOG.info(" Processing a batch for new provider record count: " + count1301);

				// executor1301.execute(soap1301ClientService);

				mviCallCount = soap1301ClientService.run();
				if (mviCallCount == maxcalls) {
					LOG.info("Max MVI call count reached " + mviCallCount);
					LOG.info(" Done processing partial batch for new provider record count: " + count1301);
					LOG.info("Done processing new provider " + count1301);
					LOG.info("Time for 1301 processing " + (new java.util.Date().getTime() - time) / 1000);
					
					return;
				}
				LOG.info(" Done processing a batch for new provider record count: " + count1301);

			}

		}
		/*
		 * for (;;) { int count = executor1301.getActiveCount();
		 * LOG.info("Active Threads : " + count); try { Thread.sleep(1000); } catch
		 * (InterruptedException e) { LOG.info("Exception in Data Fetching ... " +
		 * e.getMessage(), e); 
		 * "Exception in Data Fetching ... " + e.getMessage()); } if (count == 0) {
		 * executor1301.shutdown(); break; } }
		 */
		LOG.info("Done processing new provider " + count1301);
		LOG.info("Time for 1301 processing " + (new java.util.Date().getTime() - time) / 1000);
		
	}

	public void call1302() {

		LOG.info("Fectching new providers");
		// ThreadPoolTaskExecutor executor1302 = (ThreadPoolTaskExecutor)
		// BeanUtil.getBean(ThreadPoolTaskExecutor.class);
		mviResponseProcessor.getResponseList().clear();
		// Processing updated providers
		int count1302 = 0;
		
		LOG.info("Fectching updated providers");
		mviResponseProcessor.setMode(SoapConstants.UPDATE);
		mviResponseProcessor.getResponseList().clear();
		int totalCount = 0;
		while (getUpdateProvidersCount() > 0) {

			String ids = getUpdateProviderIds();
			@SuppressWarnings("unchecked")
			List<Object[]> nonVaProviders = entityManager.createNativeQuery(" Select distinct * From APP."
					+ CommonConstants.DB_ENV + "UpdatedVistaProviders NVA where NonVaprovider_id in (" + ids
					+ ") order by NonVaprovider_id , StationNumber , InactiveFlag desc, SpecialityCode, DeaNumber")
					.getResultList();

			int recordsCount = get1302Count(ids);
			if (CollectionUtils.isNotEmpty(nonVaProviders)) {
				count1302 = count1302 + recordsCount;
				totalCount = totalCount + recordsCount;

				Soap1302ClientService soap1302ClientService = (Soap1302ClientService) BeanUtil
						.getBean(Soap1302ClientService.class);
				LOG.info(" Updating a batch for updated provider record count: " + count1302);
				soap1302ClientService.setResults(nonVaProviders);
				soap1302ClientService.setCount(mviCallCount);
				mviCallCount = soap1302ClientService.run();
				// executor1302.execute(soap1302ClientService);
				if (mviCallCount == maxcalls) {
					LOG.info("Max MVI call count reached " + mviCallCount);
					LOG.info(" Done processing partial batch for new provider record count: " + count1302);
					LOG.info("Done processing " + count1302 + " updated providers ");
					long endtime = new Date().getTime();
					
					LOG.info("Processing time : " + (endtime - time) / 1000);
					LOG.info("Total record count : " + totalCount);
					return;

				}

				// executor1302.execute(soap1302ClientService);
				LOG.info(" Done updating a batch for updated provider record count:  " + count1302);

			}

		}
		/*
		 * for (;;) { int count = executor1302.getActiveCount();
		 * LOG.info("Active Threads : " + count); try { Thread.sleep(1000); } catch
		 * (InterruptedException e) { LOG.info("Exception in Data Fetching ... " +
		 * e.getMessage(), e); 
		 * "Exception in Data Fetching ... " + e.getMessage()); } if (count == 0) {
		 * executor1302.shutdown(); break; } }
		 */
		
		LOG.info("Done processing " + count1302 + " updated providers ");
		long endtime = new Date().getTime();
		
		LOG.info("Processing time : " + (endtime - time) / 1000);
		LOG.info("Total record count : " + totalCount);

	}

	

	public int getNewProvidersCount() {
		// @formatter:off

		int count = (int) entityManager.createNativeQuery(" Select count(distinct NVA.NonVaprovider_id) " + " From APP."
				+ CommonConstants.DB_ENV + "NewVistaProviders NVA   ").getSingleResult();

		// @formatter:on

		return count;
	}

	public int getUpdateProvidersCount() {
		// @formatter:off
		int count = (int) entityManager.createNativeQuery(" Select count(distinct NVA.NonVaprovider_id) " + " From APP."
				+ CommonConstants.DB_ENV + "UpdatedVistaProviders  NVA   ").getSingleResult();

		// @formatter:on
		return count;
	}

	public int get1301Count(String ids) {
		// @formatter:off

		int count = (int) entityManager.createNativeQuery(
				" Select count(distinct concat (NVA.NonVaprovider_id, NVA.stationNumber)) " + " From APP."
						+ CommonConstants.DB_ENV + "NewVistaProviders NVA  where NVA.NonVaprovider_id in (" + ids + ")")
				.getSingleResult();

		// @formatter:on

		return count;
	}

	public int get1302Count(String ids) {
		// @formatter:off

		int count = (int) entityManager
				.createNativeQuery(" Select count(distinct concat (NVA.NonVaprovider_id, NVA.stationNumber)) "
						+ " From APP." + CommonConstants.DB_ENV
						+ "UpdatedVistaProviders NVA where NVA.NonVaprovider_id in (" + ids + ")")
				.getSingleResult();

		// @formatter:on

		return count;
	}

	@SuppressWarnings("unchecked")
	public String getUpdateProviderIds() {

		// @formatter:off
		List<Integer> list = entityManager.createNativeQuery(" Select distinct  top " + batchSize
				+ "  NVA.NonVaprovider_id   " + " From APP." + CommonConstants.DB_ENV + "UpdatedVistaProviders NVA   "

		).getResultList();
		// @formatter:on

		return getInclause(list);
	}

	@SuppressWarnings("unchecked")
	public String getNewProviderIds() {

		// @formatter:off
		List<Integer> list = entityManager.createNativeQuery(" Select distinct  top " + batchSize
				+ "  NVA.NonVaprovider_id   " + " From APP." + CommonConstants.DB_ENV + "NewVistaProviders NVA   "

		).getResultList();
		// @formatter:on

		return getInclause(list);
	}

	public String getInclause(List<Integer> list) {

		StringBuffer ids = new StringBuffer("");
		for (Integer key : list) {
			ids.append("'" + key + "' ,");
		}
		String id = null;
		if (!StringUtils.isEmpty(ids)) {
			id = ids.toString().substring(0, ids.toString().lastIndexOf(","));
		}
		return id;

	}

}
