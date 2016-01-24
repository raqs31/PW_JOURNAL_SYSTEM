package pw.mario.faces.articles.co;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
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
import pw.mario.journal.service.LoginService;
import pw.mario.journal.service.article.ArticleService;

@Named
@ViewScoped
public class ArticleDetailsController implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String PARAM_ARTICLE_ID = "articleId";

	@Getter @Setter private Article article;
	@Inject @ArticleManagement(ArticleManager.AUTHOR) private ArticleService articleService;
	@Inject transient private LoginService ctx;
	@PostConstruct
	private void init() {
		if (article == null) {
			Article flashArticle = (Article) FacesContext.getCurrentInstance().getExternalContext().getFlash().get(PARAM_ARTICLE_ID);
			if (flashArticle != null)
				article = articleService.getArticle(flashArticle.getArticleId(), null);
			else
				article = articleService.getArticle(251L, ctx.getCurrentUser());
			
		}
	}
}
