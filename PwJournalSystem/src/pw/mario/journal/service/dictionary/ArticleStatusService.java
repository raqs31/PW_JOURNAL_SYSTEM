package pw.mario.journal.service.dictionary;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import pw.mario.journal.dao.DictionaryDAO;
import pw.mario.journal.model.Dictionary;
import pw.mario.journal.qualifiers.DictionaryType;
import pw.mario.journal.qualifiers.enums.DictType;
import pw.mario.journal.service.DictionaryService;

@DictionaryType(DictType.ARTICLE_STATUS)
@Stateless
public class ArticleStatusService implements DictionaryService {
	@Inject @DictionaryType(DictType.ARTICLE_STATUS) DictionaryDAO dao;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Dictionary getDictionary(String code) {
		return dao.getDictionary(code);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Dictionary> getDictionaries() {
		return dao.getDictionaries();
	}

}
