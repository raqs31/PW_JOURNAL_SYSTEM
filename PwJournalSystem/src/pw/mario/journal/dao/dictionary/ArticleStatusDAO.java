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
public class ArticleStatusDAO extends AbstractDAOImpl<ArticleStatus> implements DictionaryDAO<ArticleStatus> {

	@Override
	public ArticleStatus getDictionary(String code) {
		return find(code);
	}

	@Override
	public ArticleStatus addDictionary(ArticleStatus d) {
		em.persist(d);
		return getDictionary(d.getCode());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArticleStatus> getDictionaries() {
		return em.createQuery("select a from ArticleStatus a").getResultList();
	}

}
