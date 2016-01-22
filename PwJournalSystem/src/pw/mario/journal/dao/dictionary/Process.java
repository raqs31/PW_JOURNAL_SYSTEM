package pw.mario.journal.dao.dictionary;

import pw.mario.journal.model.User;

public interface Process<T, V> extends DictionaryDAO<T>{
	T getInitialProcess();
	T getAvailableSteps(T currentStep, V toProcess, User forUser);
}
