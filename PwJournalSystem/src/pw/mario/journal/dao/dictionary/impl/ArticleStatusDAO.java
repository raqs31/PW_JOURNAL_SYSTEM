package pw.mario.journal.dao.dictionary.impl;

import java.util.List;

import javax.enterprise.context.Dependent;

import pw.mario.journal.dao.AbstractDAOImpl;
import pw.mario.journal.dao.dictionary.ArticleStatusDao;
import pw.mario.journal.model.dictionary.ArticleStatus;

@Dependent
public class ArticleStatusDAO extends AbstractDAOImpl<ArticleStatus> implements ArticleStatusDao<ArticleStatus> {

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

	@Override
	public ArticleStatus getInitialProcess() {
		return em.createNamedQuery(ArticleStatus.INITIAL_PROCESS, ArticleStatus.class).getSingleResult();
	}


}
