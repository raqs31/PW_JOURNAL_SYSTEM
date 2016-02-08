package pw.mario.journal.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pw.mario.journal.model.ext.AuditTable_;

@Generated(value="Dali", date="2016-02-08T22:34:40.175+0100")
@StaticMetamodel(ArticleVersion.class)
public class ArticleVersion_ extends AuditTable_ {
	public static volatile SingularAttribute<ArticleVersion, Long> versionId;
	public static volatile SingularAttribute<ArticleVersion, Long> versionNum;
	public static volatile SingularAttribute<ArticleVersion, Boolean> lastVersion;
	public static volatile SingularAttribute<ArticleVersion, String> attachement;
	public static volatile SingularAttribute<ArticleVersion, Article> article;
	public static volatile SingularAttribute<ArticleVersion, ArticleAcceptor> acceptor;
}
