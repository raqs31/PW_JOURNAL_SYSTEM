package pw.mario.common.converter;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import lombok.extern.log4j.Log4j;
import pw.mario.journal.model.Tag;

@Log4j
@FacesConverter(forClass=Tag.class, value="pickListTagConverter")
public class PickListTagConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		PickList list = (PickList) component;
		DualListModel<Tag> source = (DualListModel<Tag>)list.getValue();
		Long id = Long.parseLong(value);
		
		Tag t = traverseList(source.getSource(), id);
		if (t == null)
			t = traverseList(source.getTarget(), id);
		if (t== null)
			log.warn("Unable to find elements for value " + value);
			
		return t;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((Tag)value).getTagId().toString();
	}
	
	
	private Tag traverseList(List<Tag> toTraverse, Long value) {
		try {
			Optional<Tag> result = toTraverse.stream().filter(t->value.compareTo(t.getTagId()) == 0).findAny();
			return result.get();
		} catch (NoSuchElementException e){}
		
		return null;
	}
}
