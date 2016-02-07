package pw.mario.faces.articles.co;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import pw.mario.faces.articles.model.ArticlesTab;
import pw.mario.journal.model.Article;
import pw.mario.journal.qualifiers.ArticleTab;
import pw.mario.journal.service.article.ArticleLazyLoadingService;

@Named
@NoArgsConstructor
@ViewScoped
public class ArticleListController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject @ArticleTab @Getter @Setter private List<ArticlesTab> articlesTabs;
	@Inject private ArticleLazyLoadingService articleLazyLoadService;
	@Getter @Setter private Article articleDetail;
	
	@PostConstruct
	private void init() { 
		articlesTabs.forEach(t -> t.refresh());
		Collections.sort(articlesTabs, new ArticlesTab.ArticleTabComparator());
	}
	
	public void loadDetails(Article a) {
		articleDetail = a;
		if (a != null) {
			articleLazyLoadService.loadAuthors(a);
			articleLazyLoadService.loadTags(a);
		}
	}
	
	public String onEdit(Article detail) {
		FacesContext.getCurrentInstance()
						.getExternalContext()
						.getFlash()
						.put(ArticleDetailsController.PARAM_ARTICLE_ID, detail);
		return "articleDetails?faces-redirect=true";
	}
	 public void onRowSelect(SelectEvent event) {
	        FacesMessage msg = new FacesMessage("Selected " + event.getObject(), null);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
}
