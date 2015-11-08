package pw.mario.journal.ejb;

import java.io.Serializable;
import java.util.LinkedList;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

import lombok.Data;

@Data
@ManagedBean(name="testBean")
@SessionScoped
public class TestBean implements Serializable {
	public TestBean() {
		testList = new LinkedList<>();
		for (int i = 0; i < 20; i++) {
			testList.add("test"+i);
		}
	}
	private String selected;
	private List<String> testList;
	
	public void onTabClose(TabCloseEvent e) {
		FacesMessage msg = new FacesMessage("TabClose", e.getTab().getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void onTabOpen(TabChangeEvent e) {
		FacesMessage msg = new FacesMessage("TabChange", e.getTab().getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
