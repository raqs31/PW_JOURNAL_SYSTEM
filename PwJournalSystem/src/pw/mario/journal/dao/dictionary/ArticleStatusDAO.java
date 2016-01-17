package pw.mario.journal.dao.dictionary;

import java.util.List;

import javax.enterprise.context.Dependent;

import pw.mario.journal.dao.AbstractDAOImpl;
import pw.mario.journal.dao.DictionaryDAO;
import pw.mario.journal.model.Dictionary;
import pw.mario.journal.model.dictionaries.ArticleStatus;
import pw.mario.journal.qualifiers.DictionaryType;
import pw.mario.journal.qualifiers.enums.DictType;

@DictionaryType(DictType.ARTICLE_STATUS)
@Dependent
public class ArticleStatusDAO extends AbstractDAOImpl<ArticleStatus> implements DictionaryDAO {

	@Override
	public ArticleStatus getDictionary(String code) {
		//TODO
		return null;
	}

	@Override
	public ArticleStatus addDictionary(Dictionary d) {
		em.persist(d);
		return getDictionary(d.getCode());
	}

	@Override
	public List<Dictionary> getDictionaries() {
		return em.createQuery("select a from ArticleStatus a").getResultList();
	}

}
