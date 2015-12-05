package pw.mario.journal.service;

import java.util.List;
import java.util.Locale;

public interface ILocaleService {

	Locale getLocaleByName(String name);

	List<Locale> getSupportedLocales();
}