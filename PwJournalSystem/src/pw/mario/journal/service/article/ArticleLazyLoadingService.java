package pw.mario.journal.service.article;

import pw.mario.journal.model.Article;

public interface ArticleLazyLoadingService {
	void loadAuthors(Article a);
	void loadTags(Article a);
}
