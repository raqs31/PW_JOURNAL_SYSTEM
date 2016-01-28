package pw.mario.common.co;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import lombok.Data;

@Data
@Named
@RequestScoped
public class AreYouSureController {
	public void onYes(ActionEvent e) {
		RequestContext.getCurrentInstance().closeDialog(Boolean.TRUE);
	}
	
	public void onNo(ActionEvent e) {
		RequestContext.getCurrentInstance().closeDialog(Boolean.FALSE);
	}
}
