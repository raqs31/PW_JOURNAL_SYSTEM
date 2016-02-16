package pw.mario.journal.service.form;

import pw.mario.common.exception.LockException;
import pw.mario.journal.model.form.Form;

public interface ModalFormService {
	Form getForm(Long formId);
	
	Form getArticleFormPattern();
	
	Form saveForm(Form form) throws LockException;
	
	Form getNewArticleForm();
}
