package pw.mario.faces.articles.co;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import pw.mario.common.util.JSFUtil;
import pw.mario.faces.articles.ArticleDetailMode;
import pw.mario.faces.articles.ArticleFlow;
import pw.mario.journal.model.Article;

@Named
@ViewScoped
public class ArticleDetailsController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private Article article;
	private ArticleDetailMode mode;
	@PostConstruct
	private void init() {
		article = (Article) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get(ArticleFlow.ARTICLE_DETAIL);
		mode =(ArticleDetailMode) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get(ArticleFlow.ARTICLE_DETAIL_MODE);
		
		if (article == null || mode == null)
			JSFUtil.redirect("articles.xhtml");
	}
}
