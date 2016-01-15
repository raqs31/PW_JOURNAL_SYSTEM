package pw.mario.journal.service;

public interface LazyLoadInitializator {
	void initialize(Object o);
	void refresh(Object o);
}
