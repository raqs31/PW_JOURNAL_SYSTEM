package pw.mario.journal.view;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.component.outputlabel.OutputLabel;

public abstract class FormUtils {
	public static ValueExpression createValueExpression(final String exp, @SuppressWarnings("rawtypes") Class clazz) {
		ExpressionFactory ef = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
		ELContext el = FacesContext.getCurrentInstance().getELContext();
		
		return ef.createValueExpression(el, "#{" + exp + "}", clazz);
	}
	
	public static ValueExpression createStrValueExpression(final String exp, @SuppressWarnings("rawtypes") Class clazz) {
		ExpressionFactory ef = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
		ELContext el = FacesContext.getCurrentInstance().getELContext();
		
		return ef.createValueExpression(el, exp, clazz);
	}
	
	public static ValueExpression createValueExpression(final Object wrapped) {
		ExpressionFactory ef = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
		ELContext el = FacesContext.getCurrentInstance().getELContext();
		
		return ef.createValueExpression(wrapped, wrapped.getClass());
	}
	
	public static UIComponent createLabel(Object label, String forItem) {
		OutputLabel out = new OutputLabel();
		out.setValue(label);
		out.setFor(forItem);
		return out;
	}
	
	public static UIComponent createLabel(String id, Object label, String forItem) {
		UIComponent out = createLabel(label, forItem);
		out.setId(id);
		return out;
	}
}
