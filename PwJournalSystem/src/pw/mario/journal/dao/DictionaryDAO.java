package pw.mario.journal.dao;

import java.util.List;

import pw.mario.journal.model.Dictionary;

public interface DictionaryDAO {
	Dictionary getDictionary(String code);
	Dictionary addDictionary(Dictionary d);
	List<Dictionary> getDictionaries();
}
