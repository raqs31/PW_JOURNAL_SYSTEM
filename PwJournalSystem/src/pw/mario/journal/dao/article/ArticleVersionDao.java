package pw.mario.journal.dao.article;

import pw.mario.journal.model.Article;
import pw.mario.journal.model.ArticleVersion;

public interface ArticleVersionDao {
	ArticleVersion getLastVersion(Article a);
	ArticleVersion getVersions(Article a);
	ArticleVersion createNewVersion(Article a);
}
