package gov.va.pie.service;

import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import gov.va.pie.common.utils.CommonConstants;

public class OutBoundMessagesServiceHelper {

	

	public static String updateProvidersQuery(List<String> providerIdList, int isProcessedStatus) {
		if (CollectionUtils.isEmpty(providerIdList) || (isProcessedStatus != 1 && isProcessedStatus != 0) ) {
			return null;
		}
		String inclause = createInClause(providerIdList);
		return "Update App."+CommonConstants.DB_ENV+"providers_V set isProcessed = " + isProcessedStatus + " where id in (" + inclause + ")";
	}
	
	

	public static String createUpdatePPMSOutboundMsgVQuery(List<String> updatesList, String httpStatusCode, String transactionId, String conversationId) {
		if (CollectionUtils.isEmpty(updatesList) || StringUtils.isEmpty(httpStatusCode) || StringUtils.isEmpty(transactionId) || StringUtils.isEmpty(conversationId))
			return null;
		
		String updateQuery = null;
		String updateIds = createInClause(updatesList);
		if (!StringUtils.isEmpty(updateIds)) {
			updateQuery = "Update App."+CommonConstants.DB_ENV+"PPMS_OutboundMsg_V set Transaction_Count = Transaction_Count+1 , transaction_date=getDate(), Outbound_Status_FK = "+httpStatusCode +", Transaction_Number=" 
					+ "'" + transactionId + "', Conversation_Number="+ "'" + conversationId + "', Modified_By='system', Modified_Date=getdate() where Provider_Id_FK in (" + updateIds + " ) ";
		}
		return updateQuery;
	}

	public static String createInsertPPMSOutboundMsgVQuery(String inClauses) {
		if (StringUtils.isEmpty(inClauses)) {
			return null;
		}
		return "Insert into App."+CommonConstants.DB_ENV+"PPMS_OutboundMsg_V (Provider_Id_FK, Outbound_Status_FK,Transaction_Count,Transaction_Date,Created_By,Created_Date,Modified_Date,Modified_By,Transaction_Number, Conversation_Number)  values "
				+ inClauses.toString().substring(0, inClauses.toString().lastIndexOf(","));
	}

	public static String createInClause(List<String> list) {
		if (CollectionUtils.isEmpty(list))
			return "";
		StringBuffer strBuffer = new StringBuffer();
		for (String id : list) {
			strBuffer.append(id + " , ");
		}
		return strBuffer.substring(0, strBuffer.toString().lastIndexOf(","));
	}

}