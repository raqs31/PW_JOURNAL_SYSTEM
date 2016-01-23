package pw.mario.common.converter;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import lombok.extern.log4j.Log4j;
import pw.mario.journal.model.Tag;
import pw.mario.journal.model.User;
import pw.mario.journal.service.UserService;

@Log4j
@FacesConverter(value="pickListUserConverter", forClass=User.class)
public class PickListUserConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		PickList list = (PickList) component;
		@SuppressWarnings("unchecked") DualListModel<User> source = (DualListModel<User>)list.getValue();
		Long id = Long.parseLong(value);
		
		User u = traverseList(source.getSource(), id);
		if (u == null)
			u = traverseList(source.getTarget(), id);
		if (u== null)
			log.warn("Unable to find elements for value " + value);
		
		return u;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		User u = (User)value;
		return u.getUserId().toString();
	}
	
	private User traverseList(List<User> toTraverse, Long value) {
		try {
			Optional<User> result = toTraverse.stream().filter(u->value.compareTo(u.getUserId()) == 0).findAny();
			return result.get();
		} catch (NoSuchElementException e){}
		
		return null;
	}

}
