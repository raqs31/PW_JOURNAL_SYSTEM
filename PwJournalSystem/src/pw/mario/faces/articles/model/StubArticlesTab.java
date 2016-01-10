package pw.mario.faces.articles.model;

import java.util.Collections;
import java.util.List;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import pw.mario.faces.common.action.UserAction;
import pw.mario.journal.model.Article;
import pw.mario.journal.qualifiers.ArticleManagement;
import pw.mario.journal.qualifiers.ArticleManager;
import pw.mario.journal.qualifiers.ArticleTab;
import pw.mario.journal.service.article.ArticleService;

@Named
@Dependent
@ArticleTab
@Priority(100)
public class StubArticlesTab implements ArticlesTab {
	@Inject @ArticleManagement(ArticleManager.STUB) private ArticleService articleService;
	@Getter @Setter private Article selectedArticle;
	
	@Override
	public List<Article> getArticles() {
		return articleService.getArticles(null);
	}

	@Override
	public List<UserAction> getActions() {
		return Collections.emptyList();
	}

	@Override
	public String getTittle() {
		return "STUBS";
	}
}
