package pw.mario.journal.dao;

import java.util.List;

public interface DictionaryDAO<T> {
	T getDictionary(String code);
	T addDictionary(T d);
	List<T> getDictionaries();
}
