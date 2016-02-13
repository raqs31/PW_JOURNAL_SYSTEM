package pw.mario.faces.articles.model.tabs;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import pw.mario.faces.articles.co.ArticleDetailsController;
import pw.mario.journal.model.article.Article;
import pw.mario.journal.qualifiers.ArticleManagement;
import pw.mario.journal.qualifiers.ArticleTab;
import pw.mario.journal.qualifiers.enums.ArticleManager;
import pw.mario.journal.service.article.ArticleService;

@Named
@ArticleTab
@Dependent
public class ManagementArticlesTab extends AbstractArticleTab implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String TITTLE = "Artykuły zarządzane";
	
	@Inject @ArticleManagement(ArticleManager.ARTICLE_MANAGER) private ArticleService articleService;
	@Getter private final String id = "articleMgmt";
	
	@Override
	public String getTittle() {
		return TITTLE;
	}

	@Override
	public String onEdit(Article a) {
		FacesContext.getCurrentInstance()
			.getExternalContext()
			.getFlash()
			.put(ArticleDetailsController.PARAM_ARTICLE_ID, a);
		return "articleDetails?faces-redirect=true";
	}
	
	@Override
	protected ArticleService getArticleService() {
		return articleService;
	}
}
