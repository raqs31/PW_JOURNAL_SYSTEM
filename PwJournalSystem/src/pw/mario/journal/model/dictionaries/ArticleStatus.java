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
	public static final String DELETABLE = "DELETE";
	
	public boolean initial() {
		return INITIAL.equals(getAttr1());
	}
	
	public boolean canDelete() {
		return DELETABLE.equals(getAttr2());
	}
}
