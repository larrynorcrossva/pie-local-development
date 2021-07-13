package gov.va.pie.common.utils;

import java.io.File;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Common Utils
 * 
 * @author Ablevets
 *
 */
public class CommonUtils {

	private static final Logger LOG = LogManager.getLogger(CommonUtils.class);



	private static final String PROC = "App.sp_" + CommonConstants.DB_ENV + "InsOutboundStatusMsg";

	/**
	 * Creates Model from JSON file for the initial load process
	 * 
	 * @param file
	 * @param model
	 * @return
	 * @throws IOException
	 */
	public static <T> T createModelFromJSONFile(File file, Class<T> model) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		T responseModel = null;

		try {

			// Configure to handle if JSON file contain more properties than Java object
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			// JSON file to Java object
			responseModel = mapper.readValue(file, model);

		} catch (JsonMappingException e) {
			LOG.info("Mapping exception");
			throw e;
		} catch (JsonParseException e) {
			LOG.info("Json parse exception");
			throw e;
		} catch (IOException e) {
			LOG.info("IOException exception");
			throw e;
		}
		return responseModel;
	}

	public static void runProcedure(EntityManager entityManager, String param, DataSource dataSource) {

		if (StringUtils.isEmpty(param)) {
			return;
		}
		//LOG.info( "Inside common utils Data source "+dataSource);
		if(dataSource!=null) {
			LOG.info( "Before calling EM CommonUtils Max Idle: " + dataSource.getMaxIdle()+" "+"Max Active: " + dataSource.getMaxActive());
			LOG.info("Before  calling EM CommonUtils Active: " + dataSource.getNumActive()+" "+"Idle: " + dataSource.getNumIdle());


		}
		
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(PROC);
		storedProcedureQuery.registerStoredProcedureParameter("insert", String.class, ParameterMode.IN);
		storedProcedureQuery.setParameter("insert", param);
		storedProcedureQuery.execute();
		entityManager.close();
		
		if(dataSource!=null) {
			LOG.info("After calling EM CommonUtils Max Idle: " + dataSource.getMaxIdle()+" "+"Max Active: " + dataSource.getMaxActive());
			LOG.info("After calling EM CommonUtils Active: " + dataSource.getNumActive()+" "+"Idle: " + dataSource.getNumIdle());


		}
		
	}

}
