package pw.mario.faces;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

import lombok.Data;
import pw.mario.journal.dao.ArticleDAO;
import pw.mario.journal.dao.UserDAO;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.User;
import pw.mario.journal.service.LoginContextService;

@Data
@ManagedBean(name="testBean")
@SessionScoped
public class TestBean implements Serializable {
	private static final long serialVersionUID = 2488630010545497455L;
	
	private String selected;
	private List<String> testList;
	private List<User> users;
	private Integer testInt;
	
	@Inject
	UserDAO ud;
	@Inject
	ArticleDAO ad;
	
	@Inject
	LoginContextService ctx; 
	
	public TestBean() {
		testList = new LinkedList<>();
		for (int i = 0; i < 20; i++) {
			testList.add("test"+i);
		}
		
		

	}
	
	public void onTabClose(TabCloseEvent e) {
		FacesMessage msg = new FacesMessage("TabClose", e.getTab().getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void onTabOpen(TabChangeEvent e) {
		FacesMessage msg = new FacesMessage("TabChange", e.getTab().getTitle());
		FacesContext.getCurrentInstance().addMessage(e.getTab().getId(), msg);
	}
	
	
    
    @PostConstruct
    public void init() {
        
        users = ud.getUsersList();
        ad.addArticle(new Article(), null);
    }
}
