package pw.mario.faces.articles.model;

import java.util.Comparator;
import java.util.List;

import pw.mario.common.action.form.ButtonAction;
import pw.mario.journal.model.Article;

public interface ArticlesTab {
	List<Article> getArticles();
	
	Iterable<ButtonAction<Article>> getActions();

	String getTittle();
	
	Article getSelectedArticle();
	void setSelectedArticle(Article a);
	
	String getId();

	String onEdit();
	
	public class ArticleTabComparator implements Comparator<ArticlesTab> {

		@Override
		public int compare(ArticlesTab o1, ArticlesTab o2) {
			return o1.getId().compareTo(o2.getId());
		}
		
	}
}
