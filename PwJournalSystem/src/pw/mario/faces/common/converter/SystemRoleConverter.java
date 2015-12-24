package pw.mario.faces.common.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import pw.mario.journal.model.SystemRole;

@FacesConverter(forClass=SystemRole.class, value="sysRoleConverter")
public class SystemRoleConverter implements Converter {

	@Override
	@SuppressWarnings("unchecked")
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		PickList p = (PickList) component;
	    DualListModel<SystemRole> dl = (DualListModel<SystemRole>) p.getValue();
	    return dl.getSource().get(Integer.valueOf(value));
	}

	@Override
	@SuppressWarnings("unchecked")
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		PickList  p = (PickList) component;
        DualListModel<SystemRole> dl = (DualListModel<SystemRole>) p.getValue();
        return  String.valueOf(dl.getSource().indexOf(value));
	}

}
