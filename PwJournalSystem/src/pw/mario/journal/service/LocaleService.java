package pw.mario.journal.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "localeService", eager = true)
@ApplicationScoped
public class LocaleService {
	private Map<String, Locale> supportedLocales;
	
	@PostConstruct
	private void init() {
		supportedLocales = new HashMap<>();
		Iterator<Locale> localeIter = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();

		while (localeIter.hasNext()) {
			Locale locale = localeIter.next();
			supportedLocales.put(locale.getDisplayName(), locale);
		}

	}

	public Locale getLocaleByName(String name) {
		return supportedLocales.get(name);
	}
	
	public List<Locale> getSupportedLocales() {
		List<Locale> localeList = new LinkedList<>();
		
		localeList.addAll(supportedLocales.values());
		
		return localeList;
	}
}
