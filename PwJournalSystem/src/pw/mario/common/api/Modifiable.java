package pw.mario.common.api;

import java.util.List;

import javax.faces.application.FacesMessage;

public interface Modifiable {
	void addChild();
	
	void delete();
	
	void order();
	
	List<FacesMessage> valid();
}
