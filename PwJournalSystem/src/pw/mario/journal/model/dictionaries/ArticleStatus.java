package pw.mario.journal.model.dictionaries;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import lombok.NoArgsConstructor;
import pw.mario.journal.model.Dictionary;

@Entity
@NoArgsConstructor
@DiscriminatorValue(ArticleStatus.DICTIONARY_NAME)
@NamedQueries({
	@NamedQuery(name=ArticleStatus.INITIAL_PROCESS,
			query = "select s from ArticleStatus s where s.attr1 =  '" + ArticleStatus.INITIAL + "'")
})
public class ArticleStatus  extends Dictionary {
	public static final String DICTIONARY_NAME = "ARTICLE_STATUS";
	public static final String INITIAL_PROCESS = "article.status.initial";
	public static final String INITIAL = "INITIAL";
	private static final String DELETABLE = "DELETE";
	private static final String ADD_VERSION = "ADD_VER";
	private static final String PRINTABLE = "FOR_PRINT";
	
	public boolean initial() {
		return INITIAL.equals(getAttr1());
	}
	
	public boolean deletable() {
		return DELETABLE.equals(getAttr2());
	}
	
	public boolean addVersionEnabled() {
		return ADD_VERSION.equals(getAttr4());
	}
	
	public boolean printable() {
		return PRINTABLE.equals(getAttr3());
	}
}
