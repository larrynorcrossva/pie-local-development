package gov.va.pie.transformer;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import gov.va.pie.cdw.model.VAProvider;
import gov.va.pie.common.entities.FirstProviderRecord;

/**
 * 
 * @author AbleVets
 *
 */
@Component
public class EntityToVaproviderTransformer {

	static Logger LOG = LogManager.getLogger(EntityToVaproviderTransformer.class);

	@Transformer
	public Message<List<VAProvider>> entityToVaProviderTranformation(
			Message<List<FirstProviderRecord>> providerEntitiesMessage) {

		List<VAProvider> vaProviderList = new ArrayList<>();
		List<FirstProviderRecord> providerEntites = (List<FirstProviderRecord>) providerEntitiesMessage.getPayload();

		for (FirstProviderRecord providerEntity : providerEntites) {
			VAProvider vaProvider = new VAProvider();
			vaProvider.setId(providerEntity.getId());
			vaProvider.setStaffSid(providerEntity.getStaffSID());
			vaProvider.setStaffIEN(providerEntity.getStaffIEN());
			vaProvider.setNpi(providerEntity.getNpi());
			vaProvider.setDea(providerEntity.getDea());
			vaProvider.setStaffName(providerEntity.getStaffName());
			vaProvider.setLastName(providerEntity.getLastName());
			vaProvider.setFirstName(providerEntity.getFirstName());
			vaProvider.setMiddleName(providerEntity.getMiddleName());
			vaProvider.setOfficePhone(providerEntity.getOfficePhone());
			vaProvider.setFaxNumber(providerEntity.getFaxNumber());
			vaProvider.setEmailAddress(providerEntity.getEmailAddress());
			vaProvider.setGender(providerEntity.getGender());
			vaProvider.setX12Code(providerEntity.getX12Code());
			vaProvider.setSta3n(providerEntity.getSta3n());
			vaProvider.setSta6a(providerEntity.getSta6a());
			vaProvider.setVisn(providerEntity.getVisn());
			vaProvider.setTerminationDate(providerEntity.getTerminationDate());
			vaProvider.setRegion(providerEntity.getRegion());
			vaProvider.setStreetAddress1(providerEntity.getStreetAddress1());
			vaProvider.setStreetAddress2(providerEntity.getStreetAddress2());
			vaProvider.setCity(providerEntity.getCity());
			vaProvider.setState(providerEntity.getState());
			vaProvider.setZip(providerEntity.getZip());
			vaProvider.setCreatedBy(providerEntity.getCreatedBy());
			vaProvider.setCreatedDate(providerEntity.getCreatedDate());
			vaProvider.setModifiedBy(providerEntity.getCreatedBy());
			vaProvider.setModifiedDate(providerEntity.getModifiedDate());
			vaProvider.setIsProcessed(providerEntity.getIsProcessed());
			vaProviderList.add(vaProvider);
		}

		Message<List<VAProvider>> message = null;
		if (providerEntitiesMessage.getHeaders().get("manual") != null) {
			message = MessageBuilder.withPayload(vaProviderList)
					.setHeader("manual", providerEntitiesMessage.getHeaders().get("manual")).build();

		} else {
			message = MessageBuilder.withPayload(vaProviderList).build();
		}
		return message;
	}

}
