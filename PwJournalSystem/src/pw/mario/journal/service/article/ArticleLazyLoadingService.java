package pw.mario.journal.service.article;

import pw.mario.journal.model.article.Article;

public interface ArticleLazyLoadingService {
	void loadAuthors(Article a);
	void loadTags(Article a);
	void loadAcceptors(Article a);
}
