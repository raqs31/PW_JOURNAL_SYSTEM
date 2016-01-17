package pw.mario.journal.dao.dictionary;

import pw.mario.journal.model.Dictionary;

public interface DictionaryDAO<T extends Dictionary> {
	T getDictionary(String code);
	T addDictionary(T dictionary);
	T getDictionaries();
}
