package pw.mario.journal.dao;

import pw.mario.journal.model.Article;
import pw.mario.journal.model.User;

public interface ArticleDAO {
	public void addArticle(Article a, User u);
}
