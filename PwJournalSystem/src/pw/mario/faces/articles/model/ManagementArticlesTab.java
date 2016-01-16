package pw.mario.faces.articles.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import pw.mario.common.action.form.ButtonAction;
import pw.mario.journal.model.Article;
import pw.mario.journal.qualifiers.ArticleManagement;
import pw.mario.journal.qualifiers.ArticleManager;
import pw.mario.journal.qualifiers.ArticleTab;
import pw.mario.journal.service.LoginService;
import pw.mario.journal.service.article.ArticleService;

@Named
@ArticleTab
@Dependent
@Priority(1)
public class ManagementArticlesTab implements Serializable, ArticlesTab {
	private static final long serialVersionUID = 1L;
	private static final String TITTLE = "Artykuły zarządzane";
	
	@Inject @ArticleManagement(ArticleManager.ARTICLE_MANAGER) private ArticleService articleService;
	@Inject private LoginService ctx;
	
	@Getter @Setter private List<Article> articles;
	@Getter @Setter private Article selectedArticle;
	@Getter private final String id = "articleMgmt";
	
	@PostConstruct
	private void init() {
		articles = articleService.getArticles(ctx.getCurrentUser());
	}

	@Override
	public List<ButtonAction> getActions() {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

	@Override
	public String getTittle() {
		return TITTLE;
	}

	@Override
	public String onEdit() {
		// TODO Auto-generated method stub
		return "articleDetail";
	}

}
