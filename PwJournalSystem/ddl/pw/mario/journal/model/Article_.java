package pw.mario.journal.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pw.mario.journal.model.dictionaries.ArticleStatus;
import pw.mario.journal.model.ext.AuditTable_;

@Generated(value="Dali", date="2016-01-28T00:03:59.685+0100")
@StaticMetamodel(Article.class)
public class Article_ extends AuditTable_ {
	public static volatile SingularAttribute<Article, Long> articleId;
	public static volatile SingularAttribute<Article, String> name;
	public static volatile SingularAttribute<Article, String> description;
	public static volatile SingularAttribute<Article, Integer> year;
	public static volatile SingularAttribute<Article, Integer> month;
	public static volatile SingularAttribute<Article, Integer> day;
	public static volatile SingularAttribute<Article, User> management;
	public static volatile SetAttribute<Article, User> authors;
	public static volatile SetAttribute<Article, Tag> tagList;
	public static volatile ListAttribute<Article, ArticleVersion> versions;
	public static volatile SetAttribute<Article, User> acceptors;
	public static volatile SingularAttribute<Article, ArticleStatus> status;
	public static volatile ListAttribute<Article, ArticleHistory> history;
}
