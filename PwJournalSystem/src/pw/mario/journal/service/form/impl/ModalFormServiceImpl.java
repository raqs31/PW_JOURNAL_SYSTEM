package pw.mario.journal.service.form.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import pw.mario.journal.dao.form.ModalFormDao;
import pw.mario.journal.model.form.Form;
import pw.mario.journal.service.form.ModalFormService;

@ApplicationScoped
public class ModalFormServiceImpl implements ModalFormService {
	@Inject private ModalFormDao formDao;

	@Override
	public Form getForm(Long formId) {
		return formDao.getForm(formId);
	}

	@Override
	public Form getArticleFormPattern() {
		return formDao.getLockedForm(formDao.getFormPattern(Form.PatternCode.ARTICLE_ACCEPTOR));
	}

	@Override
	public Form saveForm(Form form) {
		return formDao.saveForm(form);
	}
	
	

}
