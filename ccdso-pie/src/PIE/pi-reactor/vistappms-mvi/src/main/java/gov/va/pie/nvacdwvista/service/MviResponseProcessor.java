package gov.va.pie.nvacdwvista.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import gov.va.pie.common.utils.CommonConstants;
import gov.va.pie.nvacdwvista.model.MviMessageCode;
import gov.va.pie.nvacdwvista.model.MviResponse;
import gov.va.pie.nvacdwvista.util.SoapConstants;
import gov.va.pie.nvappmscdw.utils.NvaUtils;

@Service
@Scope("prototype")
public class MviResponseProcessor {

	private MviMessageCode mviMessageCode;
	private String mode;
	private List<MviResponse> responseList;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private DataSource ds;

	@Value("${mvi.without.sr2350}")
	private boolean mviWithoutSR2350;
	
	@Transactional
	public void saveBulkResponse() {
		if (!CollectionUtils.isEmpty(responseList)) {
			List<List<MviResponse>> providersList = Lists.partition(responseList, 1000);
			for (List<MviResponse> list : providersList) {
				
				NvaUtils.runProcedureInTransaction(entityManager, getBulkDeleteQuery(list), ds);
				NvaUtils.runProcedureInTransaction(entityManager, getBulkInsertVisaOutboundResponseQuery(list) ,ds);
				//NvaUtils.runProcedureInTransaction(entityManager, getBulkUpdateProviders(list));
				
				if (mviWithoutSR2350 && SoapConstants.UPDATE.equals(mode)) {
					NvaUtils.runProcedureInTransaction(entityManager, getBulkUpdateDeleteQuery(list), ds);
				}

				if (mviWithoutSR2350 && SoapConstants.UPDATE.equals(mode)) {
					NvaUtils.runProcedureInTransaction(entityManager, getBulkUpdateQuery(list), ds);
				}

			}
		}

	}
	public String getBulkUpdateDeleteQuery(List<MviResponse> list) {
		StringBuilder updateKeys = new StringBuilder();
		for (MviResponse response : list) {
			updateKeys.append(response.getNvaProviderId()).append(" , ");
			 	
		}
		String updateQuery ="   Update V1 set V1.ActionCode=V2.ActionCode , V1.VistAResponseCode=V2.VistAResponseCode, V1.Response_Message_Text='Deleted at '+V2.StationNo+'  '+V2.Response_Message_Text, "
							+"  V1.Modified_Date=V2.Modified_Date,  V1.hasupdates=V2.hasupdates "
							+"  From "
							+"  APP."+ CommonConstants.DB_ENV +"VistA_OutResponse_V  V1"
							+"  inner join  APP."+ CommonConstants.DB_ENV + "VistA_OutResponse_V  V2 on (V1.NonVAProvider_Id_FK=V2.NonVAProvider_Id_FK) "
							+"  where V1.ActionCode!='delete' and V2.ActionCode='delete' and V2.NonVAProvider_Id_FK  in ( "
							+ updateKeys.toString().substring(0, updateKeys.toString().lastIndexOf(",")) + " ) ";
	
		return updateQuery;
	}

	@PostConstruct
	private void initialize() {
		responseList = new ArrayList<>();
		mviMessageCode = new MviMessageCode();
	}

	public String getBulkDeleteQuery(List<MviResponse> list) {
		StringBuilder deleteKeysBuilder = new StringBuilder();                                       
		for (MviResponse response : list) {
			deleteKeysBuilder.append("(NonVAProvider_Id_FK = " +  response.getNvaProviderId() +  " and Stationno = " + response.getStation() + ") or ");
		}
		String deleteQuery = "Delete from APP." + CommonConstants.DB_ENV
				+ "VistA_OutResponse_V where [VistA_OutResponse_Id] in ( select VistA_OutResponse_Id FROM APP." + CommonConstants.DB_ENV + "VistA_OutResponse_V where " 
				+ deleteKeysBuilder.toString().substring(0, deleteKeysBuilder.toString().lastIndexOf("or")) + " ) ;";
		return deleteQuery;
	}

	public String getBulkInsertVisaOutboundResponseQuery(List<MviResponse> list) {
		StringBuilder insertBuilder = new StringBuilder();

		for (MviResponse response : list) {
			String isFailed = "AA".equals(response.getActionCode()) ? "0" : "1";
			// @formatter:off
			insertBuilder.append("(" + response.getNvaProviderId() + ", '" + response.getVistaCode() + "' , " + isFailed
					+ " , '" + response.getMessage() + "' , 'PIE', getdate()," + response.getStation() + ", '"
					+ response.getActionCode() + "', 'PIE', getdate(),0) , ");
			// @formatter:on
		}
		// @formatter:off
		String insertQuery = " Insert into APP." + CommonConstants.DB_ENV + "VistA_OutResponse_V "
				+ " (NonVAProvider_Id_FK,VistAResponseCode,IsFail,Response_Message_Text,Created_By,Created_Date, "
				+ " stationno, ActionCode, Modified_by, Modified_Date,hasUpdates)" + " values  "

				+ " " + insertBuilder.toString().substring(0, insertBuilder.toString().lastIndexOf(",")) + " ;";
		// @formatter:on
		return insertQuery;
	}

	public String getBulkUpdateProviders(List<MviResponse> list) {
		StringBuilder updateKeys = new StringBuilder();
		for (MviResponse response : list) {
			updateKeys.append(response.getNvaProviderId()).append(" , ");
		}
		// @formatter:off
		String updateQuery = "Update App." + CommonConstants.DB_ENV + "VistA_OutResponse_V set hasUpdates = 0 , Modified_by = 'PIE', Modified_Date = getdate() "
			             	+ " where NONVAPROVIDER_ID_FK in (" +updateKeys.toString().substring(0, updateKeys.toString().lastIndexOf(","))+ ")";
				
				
	  // @formatter:on
		return updateQuery;
	}

	public String getBulkUpdateOutBoundResponse(List<MviResponse> list) {
		StringBuilder updateKeys = new StringBuilder();
		for (MviResponse response : list) {
			updateKeys.append(response.getNvaProviderId()).append(" , ");
		}
		// @formatter:off
		String updateQuery = " Update  NVAP set isprocessed= 1" + " From   APP." + CommonConstants.DB_ENV
				+ "NonVAProvider_V NVAP " + " where NVAP.NonVAProvider_Id in ( "
				+ updateKeys.toString().substring(0, updateKeys.toString().lastIndexOf(",")) + ") ";

		// @formatter:on
		return updateQuery;
	}

	public String getBulkUpdateQuery(List<MviResponse> list) {
		StringBuilder updateKeys = new StringBuilder();

		for (MviResponse response : list) {
			updateKeys.append("(V2.NonVAProvider_Id_FK = " +  response.getNvaProviderId() +  " and V2.Stationno = " + response.getStation() + ") or ");
		}

		String updateQuery ="   Update V1 set V1.ActionCode=V2.ActionCode , V1.VistAResponseCode=V2.VistAResponseCode, V1.Response_Message_Text='Updated at '+V2.StationNo+'  '+V2.Response_Message_Text, "
							+"  V1.Modified_Date=V2.Modified_Date,  V1.hasupdates=V2.hasupdates "
							+"  From "
							+"  APP."+ CommonConstants.DB_ENV +"VistA_OutResponse_V  V1"
							+"  inner join  APP."+ CommonConstants.DB_ENV + "VistA_OutResponse_V  V2 on (V1.NonVAProvider_Id_FK=V2.NonVAProvider_Id_FK) "
							+"  where V1.hasUpdates = 1 and V2.ActionCode='Update' and ("
							+ updateKeys.toString().substring(0, updateKeys.toString().lastIndexOf("or")) + " ) ";
	
		return updateQuery;
	}

	public List<MviResponse> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<MviResponse> responseList) {
		this.responseList = responseList;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public MviMessageCode getMviMessageCode() {
		return mviMessageCode;
	}

	public void populateMessageCode(String msg, String code) {
		mviMessageCode = new MviMessageCode();
		this.mviMessageCode.setCode(code);
		this.mviMessageCode.setMessage(msg);
	}

}
