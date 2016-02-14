package pw.mario.journal.model.form;

import java.util.List;

import javax.faces.application.FacesMessage;

public interface Modifiable {
	void addChild();
	
	void delete();
	
	void order();
	
	List<FacesMessage> valid();
}
