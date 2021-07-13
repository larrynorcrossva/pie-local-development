package gov.va.pie.nvappms.cdw.serviceactivator;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import gov.va.pie.common.entities.CernerFacility;
import gov.va.pie.common.entities.CernerProvider;
import gov.va.pie.common.repositories.CernerFacilityRepository;
import gov.va.pie.common.repositories.CernerProviderRepository;
import gov.va.pie.common.service.PieLogService;
import gov.va.pie.common.utils.CommonConstants;

@Component("cernerActivator")
public class CernerActivator {
	
	static final Logger LOG = LogManager.getLogger(CernerActivator.class);

	
	@Autowired
	private CernerProviderRepository cernerProviderRepo;
	
	@Autowired
	private CernerFacilityRepository cernerFacilityRepo;
	
	@Autowired
	private PieLogService pieLogService;
	
	@Value("${ppms.cerner.output_path}")
	private String outputPath;
	
	@Value("${ppms.cerner.batch_size}")
	private int batchSize;
	
	@ServiceActivator
	@Async
	public void processCernerData(Message<String> processMessage) {

		String payload = processMessage.getPayload();
		LOG.info(payload);
		
		List<CernerProvider> providers;
		if(payload.equals("")) {
			providers = cernerProviderRepo.findAll();
		} else {
			String start = payload.substring(0, payload.indexOf(' '));
			String end = payload.substring(payload.indexOf(' ') +1);
			providers = cernerProviderRepo.findBetweenDates(start, end);
		}
		
		for(int fileNum = 0; fileNum * batchSize < providers.size(); fileNum++) {
			List<List<String>> providerFields = new ArrayList<List<String>>();
			providerFields.add(getProviderHeader());
			for (int i = fileNum * batchSize; i < fileNum * batchSize + batchSize && i < providers.size(); i++) {
				CernerProvider cp = providers.get(i);
				providerFields.add(cp.toFieldList());
			}
							
			try {
				FileWriter fw = new FileWriter(getFileName(true,new Date(),fileNum +1));
				CSVPrinter printer = new CSVPrinter(fw, CSVFormat.DEFAULT.withRecordSeparator("\n"));
				printer.printRecords(providerFields);
				printer.close();
				fw.close();
			} catch (IOException e) {
				pieLogService.recordDetails(e, CommonConstants.LOG_ERROR);
			}
		}
		
		
		
		List<CernerFacility> facilities;
		if(payload.equals("")) {
			facilities = cernerFacilityRepo.findAll();
		} else {
			String start = payload.substring(0, payload.indexOf(' '));
			String end = payload.substring(payload.indexOf(' ') +1);
			facilities = cernerFacilityRepo.findBetweenDates(start, end);
		}
		
		for(int fileNum = 0; fileNum * batchSize < facilities.size(); fileNum++) {
			List<List<String>> facilityFields = new ArrayList<List<String>>();
			facilityFields.add(getFacilityHeader());
			for (int i = fileNum * batchSize; i < fileNum * batchSize + batchSize && i < facilities.size(); i++) {
				CernerFacility cf = facilities.get(i);
				facilityFields.add(cf.toFieldList());
			}
							
			try {
				FileWriter fw = new FileWriter(getFileName(false,new Date(), fileNum+1));
				CSVPrinter printer = new CSVPrinter(fw, CSVFormat.DEFAULT.withRecordSeparator("\n"));
				printer.printRecords(facilityFields);
				printer.close();
				fw.close();
			} catch (IOException e) {
				pieLogService.recordDetails(e, CommonConstants.LOG_ERROR);
			}
		}
	}
	
	
	
	public String getFileName(boolean isProvider,Date d, int part) {
		SimpleDateFormat mmddyyy = new SimpleDateFormat("MMddYYYY");
		String type = isProvider ? "Providers" : "Facilities";
		if(part == -1) { return "PPMS_"+type+"_" + mmddyyy.format(d); }
		return outputPath + "PPMS_"+type+"_" + mmddyyy.format(d) +"_part" + part +".csv"; 
	}
	
	public static List<String> getProviderHeader(){
		ArrayList<String> l = new ArrayList<>();
		l.add("ProviderLastName");
	
		l.add("ProviderFirstName");
		l.add("ProviderNpi");
		l.add("AddressStreet1");
		l.add("AddressStreet2");
		l.add("City");
		l.add("State");
		l.add("ZipCode");
		l.add("MainSitePhone");
		l.add("CareSiteFax");
		l.add("ProviderStatusReason");
		l.add("SpecialityCode");
		l.add("ProviderNetwork");
		l.add("ProviderServiceStatusReason");
		l.add("DeaNumber");
		l.add("ExpirationDate");
		l.add("HasScheduleII");
		l.add("HasScheduleIII");
		l.add("HasScheduleIV");
		l.add("HasScheduleV");
		l.add("HasScheduleIINonNarcotic");
		l.add("HasScheduleIIINonNarcotic");
		l.add("DEAStatusReason");
		return l;
	}
	public List<String> getFacilityHeader(){
		ArrayList<String> l = new ArrayList<String>();
		l.add("CareSiteGuid");
		l.add("CareSiteName");
		l.add("AddressStreet1");
		l.add("AddressStreet2");
		l.add("City");
		l.add("State");
		l.add("ZipCode");
		l.add("Country");
		l.add("MainSitePhone");
		l.add("CareSiteFax");
		l.add("CareSiteStatusReason");
		
		return l;
	}
}
