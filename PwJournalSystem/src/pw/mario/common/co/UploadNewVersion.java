package pw.mario.common.co;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

@Named
@RequestScoped
public class UploadNewVersion {
	public void uploadListener(FileUploadEvent e) {
		RequestContext.getCurrentInstance().closeDialog(e.getFile());
	}
}
