package pw.mario.journal.model.dictionaries;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;
import pw.mario.journal.model.Dictionary;

@Entity
@NoArgsConstructor
@DiscriminatorValue(ArticleStatus.DICTIONARY_NAME)
public class ArticleStatus  extends Dictionary {
	public static final String DICTIONARY_NAME = "ARTICLE_STATUS";
}
