package pw.mario.journal.service.common.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

import lombok.NoArgsConstructor;
import pw.mario.journal.service.common.LocaleService;

@NoArgsConstructor
@SessionScoped
public class LocaleServiceImpl implements LocaleService {
	private static final long serialVersionUID = 1L;
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

	/* (non-Javadoc)
	 * @see pw.mario.journal.service.impl.ILocaleService#getLocaleByName(java.lang.String)
	 */
	@Override
	public Locale getLocaleByName(String name) {
		return supportedLocales.get(name);
	}
	
	/* (non-Javadoc)
	 * @see pw.mario.journal.service.impl.ILocaleService#getSupportedLocales()
	 */
	@Override
	public List<Locale> getSupportedLocales() {
		List<Locale> localeList = new LinkedList<>();
		
		localeList.addAll(supportedLocales.values());
		
		return localeList;
	}
}
