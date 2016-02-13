package pw.mario.journal.action.co;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import lombok.extern.log4j.Log4j;
import pw.mario.common.exception.PerformActionException;
import pw.mario.journal.service.common.FileManagerService;

@Log4j
@Named
@RequestScoped
public class UploadNewVersion {
	@Inject private FileManagerService service;
	public void uploadListener(FileUploadEvent e) {
		try {
			RequestContext.getCurrentInstance().closeDialog(service.saveTmpFile(e.getFile()));
		} catch (PerformActionException e1) {
			log.error("Error creating tmpFile", e1);
		}
	}
}
