package pw.mario.faces.articles.model;

import java.util.Comparator;
import java.util.List;

import org.primefaces.event.SelectEvent;

import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.api.Refreshable;
import pw.mario.journal.model.Article;

public interface ArticlesTab extends Refreshable {
	List<Article> getArticles();
	
	Iterable<ButtonAction> getActions();

	String getTittle();
	
	Article getSelectedArticle();
	void setSelectedArticle(Article a);
	
	String getId();

	String onEdit(Article a);

	boolean tabAllowed();
	
	void onRowSelect(SelectEvent e);
	
	class ArticleTabComparator implements Comparator<ArticlesTab> {

		@Override
		public int compare(ArticlesTab o1, ArticlesTab o2) {
			return -o1.getId().compareTo(o2.getId());
		}
		
	}
}
