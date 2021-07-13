package gov.va.pie.nvappms.cdw.serviceactivator;

import java.io.IOException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.TransportException;
import net.schmizz.sshj.userauth.UserAuthException;
import net.schmizz.sshj.xfer.FileSystemFile;
import net.schmizz.sshj.xfer.LocalFileFilter;
import net.schmizz.sshj.xfer.LocalSourceFile;
@Component("sftpActivator")
public class SFTPActivator {
	
	static final Logger LOG = LogManager.getLogger(SFTPActivator.class);
	
	@Value("${ppms.cerner.sftp_host}")
	private String host;
	@Value("${ppms.cerner.sftp_user}")
	private String user;
	@Value("${ppms.cerner.sftp_password}")
	private String password;
	@Value("${ppms.cerner.output_path}")
	private String outputPath;
	@Value("${ppms.cerner.sftp_hostkey}")
	private String hostKey;
	@Value("${ppms.cerner.sftp_port}")
	private int port;
	@Value("${ppms.cerner.sftp_dir}")
	private String sftpDir;
	
	@Autowired
	private CernerActivator ca;
	
	@ServiceActivator
	@Async
	public void sftpTask(Message<String> message) throws IOException {
		String payload = message.getPayload();
		LOG.info(payload);
		ca.processCernerData(message);
		
		String providerFileName = ca.getFileName(true, new Date(), -1);
		String facilityFileName = ca.getFileName(false, new Date(), -1);
		LocalFileFilter filter = new LocalFileFilter () {
			@Override
			public boolean accept(LocalSourceFile file) {
				return file.getName().contains(facilityFileName) || file.getName().contains(providerFileName);
			}
		};
		
		SSHClient ssh = new SSHClient();
		try {
			ssh.addHostKeyVerifier(hostKey);
			ssh.connect(host, port);
		}catch(IOException e) {
			LOG.error(e.getMessage());
			throw e;
		}
		FileSystemFile dir = new FileSystemFile(outputPath);
		
		try {
		    ssh.authPassword(user, password);
		    SFTPClient sftp = ssh.newSFTPClient();
		    for(FileSystemFile f : dir.getChildren(filter)) {
			    sftp.put(f, sftpDir + f.getName());
		    }
		    sftp.close();
		} catch(UserAuthException uae){
			LOG.error(uae.getMessage());
			LOG.error(uae.getDisconnectReason().name());
			throw uae;
		}catch(TransportException uae){
			LOG.error(uae.getMessage());
			LOG.error(uae.getDisconnectReason().name());
			throw uae;
		} catch(IOException ioe){
			LOG.error(ioe.getMessage());
			throw ioe;
		}
		
		try {
			ssh.disconnect();
		}catch(IOException e2) {
			LOG.error(e2.getMessage());
			throw e2;
		}
	}
}
