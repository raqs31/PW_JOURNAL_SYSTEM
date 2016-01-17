package pw.mario.journal.service;

import java.util.List;

import pw.mario.journal.model.Dictionary;

public interface DictionaryService {
	Dictionary getDictionary(String code);
	
	List<Dictionary> getDictionaries();
}
