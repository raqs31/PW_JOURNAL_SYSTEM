package pw.mario.journal.dao;

import java.util.List;

import pw.mario.journal.model.Dictionary;

public interface DictionaryDAO<T> {
	T getDictionary(String code);
	T addDictionary(T d);
	List<T> getDictionaries();
}
