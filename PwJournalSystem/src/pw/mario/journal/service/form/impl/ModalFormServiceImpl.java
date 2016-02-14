package pw.mario.journal.service.form.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import pw.mario.common.exception.LockException;
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
	@Transactional(value=TxType.REQUIRED)
	public Form getArticleFormPattern() {
		return formDao.getFormPattern(Form.PatternCode.ARTICLE_ACCEPTOR);
	}

	@Override
	@Transactional(value=TxType.REQUIRED, rollbackOn=Exception.class)
	public Form saveForm(Form form) throws LockException{
		Form checkVer = formDao.getLockedForm(form);
		if (checkVer.getObjectVersionNumber().compareTo(form.getObjectVersionNumber()) != 0)
			throw new LockException("Nie udało się zapisać", form);
		return formDao.saveForm(form);
	}
	
	

}
