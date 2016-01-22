package pw.mario.faces.articles.model.tabs;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import pw.mario.common.action.form.ButtonAction;
import pw.mario.faces.articles.ArticleDetailMode;
import pw.mario.faces.articles.ArticleFlow;
import pw.mario.faces.articles.model.ArticlesTab;
import pw.mario.journal.model.Article;
import pw.mario.journal.qualifiers.ArticleManagement;
import pw.mario.journal.qualifiers.ArticleTab;
import pw.mario.journal.qualifiers.enums.ArticleManager;
import pw.mario.journal.service.article.ArticleService;

@Named
@Dependent
@ArticleTab
public class StubArticlesTab implements ArticlesTab {
	@Inject @ArticleManagement(ArticleManager.STUB) private ArticleService articleService;
	@Getter @Setter private Article selectedArticle;
	@Getter private final String id = "stub";
	@Getter @Setter private List<Article> articles;
	
	@PostConstruct
	private void init() {
		articles = articleService.getArticles(null);
	}

	@Override
	public Iterable<ButtonAction<Article>> getActions() {
		return Collections.emptyList();
	}

	@Override
	public String getTittle() {
		return "STUBS";
	}

	@Override
	public String onEdit() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put(ArticleFlow.ARTICLE_DETAIL_MODE,ArticleDetailMode.STUB);
		flash.put(ArticleFlow.ARTICLE_DETAIL, selectedArticle);
		return "articleDetails?faces-redirect=true";
	}
	
	@Override
	public boolean tabAllowed() {
		return true;
	}

	@Override
	public void refreshActions() {
		// TODO Auto-generated method stub
		
	}
}