package pw.mario.journal.dao.article;

import java.util.List;

import pw.mario.journal.model.Article;
import pw.mario.journal.model.ArticleVersion;

public interface ArticleVersionDao {
	ArticleVersion getLastVersion(Article a);
	List<ArticleVersion> getVersions(Article a);
	ArticleVersion createNewVersion(Article a);
	String createArticleName(ArticleVersion version);
	ArticleVersion save(ArticleVersion v);
}
