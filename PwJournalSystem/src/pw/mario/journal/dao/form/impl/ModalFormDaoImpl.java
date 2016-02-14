package pw.mario.journal.dao.form.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;

import pw.mario.journal.dao.AbstractDAOImpl;
import pw.mario.journal.dao.form.ModalFormDao;
import pw.mario.journal.model.form.Form;
import pw.mario.journal.model.form.Form.PatternCode;

@Default
@Dependent
public class ModalFormDaoImpl extends AbstractDAOImpl<Form> implements ModalFormDao {

	@Override
	public List<Form> getPatterns() {
		return createNamedTypedQuery(Form.Queries.PATTERN_FORMS).getResultList();
	}

	@Override
	public Form saveForm(Form form) {
		return merge(form);
	}

	@Override
	public Form getLockedForm(Form form) {
		if (form == null)
			return null;
		return getLockedForm(form.getFormId());
	}

	@Override
	public Form getLockedForm(Long formId) {
		if (formId == null)
			return null;
		return locked(formId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
	}

	@Override
	public Form getForm(Long formId) {
		return find(formId);
	}

	@Override
	public Form getFormPattern(PatternCode pattern) {
		try {
			return createNamedTypedQuery(Form.Queries.NAMED_PATTERN).setParameter(1, pattern).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
