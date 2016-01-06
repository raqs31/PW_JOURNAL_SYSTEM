package pw.mario.journal.service;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public interface LocaleService extends Serializable {

	Locale getLocaleByName(String name);

	List<Locale> getSupportedLocales();
}