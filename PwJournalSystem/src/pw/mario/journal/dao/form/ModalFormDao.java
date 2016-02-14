package pw.mario.journal.dao.form;

import java.util.List;

import pw.mario.journal.model.form.Form;

public interface ModalFormDao {
	List<Form> getPatterns();
	
	Form saveForm(Form form);
	
	Form getLockedForm(Form form);
	
	Form getLockedForm(Long formId);
	
	Form getForm(Long formId);
	
	Form getFormPattern(Form.PatternCode pattern);
}
