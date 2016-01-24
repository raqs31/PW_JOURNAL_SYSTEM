package pw.mario.faces.articles.co;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import pw.mario.common.util.JSFUtil;
import pw.mario.faces.articles.ArticleDetailMode;
import pw.mario.faces.articles.ArticleFlow;
import pw.mario.journal.model.Article;
import pw.mario.journal.qualifiers.ArticleManagement;
import pw.mario.journal.qualifiers.enums.ArticleManager;
import pw.mario.journal.service.article.ArticleService;

@Named
@ViewScoped
public class ArticleDetailsController implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String PARAM_ARTICLE_ID = "articleId";

	@Getter @Setter private Article article;
	
	@Inject @ArticleManagement(ArticleManager.AUTHOR) private ArticleService articleService;
	
	@PostConstruct
	private void init() {
		String articleId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(PARAM_ARTICLE_ID);
		article = articleService.getArticle(Long.parseLong(articleId), null);
		
	}
}
