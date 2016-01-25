package pw.mario.journal.dao.dictionary;

import java.util.List;

import pw.mario.journal.model.Article;
import pw.mario.journal.model.Rule;
import pw.mario.journal.model.User;
import pw.mario.journal.model.dictionaries.ArticleStatus;

public interface ArticleStatusDao<T> {
	T getDictionary(String code);
	T addDictionary(T d);
	List<T> getDictionaries();
	ArticleStatus getInitialProcess();
}
