package pw.mario.journal.dao.article;

import java.util.List;

import pw.mario.journal.model.article.Article;
import pw.mario.journal.model.article.ArticleVersion;

public interface ArticleVersionDao {
	ArticleVersion getLastVersion(Article a);
	List<ArticleVersion> getVersions(Article a);
	ArticleVersion createNewVersion(Article a);
	String createArticleName(ArticleVersion version);
	ArticleVersion save(ArticleVersion v);
	void refresh(ArticleVersion v);
}
