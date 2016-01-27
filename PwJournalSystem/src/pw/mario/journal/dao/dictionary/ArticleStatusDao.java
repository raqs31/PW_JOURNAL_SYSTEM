package pw.mario.journal.dao.dictionary;

import java.util.List;

import pw.mario.journal.model.dictionaries.ArticleStatus;

public interface ArticleStatusDao<T> {
	T getDictionary(String code);
	T addDictionary(T d);
	List<T> getDictionaries();
	ArticleStatus getInitialProcess();
}
