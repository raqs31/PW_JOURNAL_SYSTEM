package pw.mario.journal.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pw.mario.journal.model.ext.AuditTable_;

@Generated(value="Dali", date="2015-12-28T10:47:25.952+0100")
@StaticMetamodel(Article.class)
public class Article_ extends AuditTable_ {
	public static volatile SingularAttribute<Article, Long> articleId;
	public static volatile SingularAttribute<Article, User> managementId;
	public static volatile ListAttribute<Article, User> authors;
	public static volatile ListAttribute<Article, Tag> tagList;
	public static volatile ListAttribute<Article, ArticleVersion> versions;
}
