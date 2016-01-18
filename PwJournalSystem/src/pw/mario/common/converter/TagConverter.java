package pw.mario.common.converter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import pw.mario.journal.model.Tag;
import pw.mario.journal.service.TagService;

@Named("tagConverter")
@ApplicationScoped
public class TagConverter implements Converter {
	@Inject private TagService service;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return service.getTag(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((Tag)value).getName();
	}
	
}
