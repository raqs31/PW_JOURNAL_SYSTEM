package pw.mario.faces.credential.converter;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import pw.mario.journal.service.common.LocaleService;

@ManagedBean(name="localeConverter", eager=true)
public class LocaleConverter implements Converter {
	@Inject
	LocaleService localeService;
	
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.isEmpty()) {
			return localeService.getLocaleByName(value);
		}
		return null;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null)
			return ((Locale)value).getDisplayName();
		return null;
	}

}
