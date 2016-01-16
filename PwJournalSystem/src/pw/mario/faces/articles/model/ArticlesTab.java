package pw.mario.faces.articles.model;

import java.util.List;

import javax.faces.event.ActionEvent;

import pw.mario.faces.common.action.form.ButtonAction;
import pw.mario.journal.model.Article;

public interface ArticlesTab {
	List<Article> getArticles();
	
	List<ButtonAction> getActions();

	String getTittle();
	
	Article getSelectedArticle();
	void setSelectedArticle(Article a);
	
	String getId();

	String onEdit();
}
