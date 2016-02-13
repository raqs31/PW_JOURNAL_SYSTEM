package pw.mario.journal.dao.dictionary.impl;

import java.util.List;

import javax.enterprise.context.Dependent;

import pw.mario.journal.dao.AbstractDAOImpl;
import pw.mario.journal.dao.dictionary.ValidationRulesDao;
import pw.mario.journal.model.article.Rule;
import pw.mario.journal.model.dictionary.ValidationRule;

@Dependent
public class ValidationRulesDaoImpl extends AbstractDAOImpl<ValidationRule> implements ValidationRulesDao {

	@Override
	public List<ValidationRule> getValidations(Rule rule) {
		return createTypedQuery("select v from Rule r join r.validations v where r = ?1")
				.setParameter(1, rule)
				.getResultList();
	}

	@Override
	public List<ValidationRule> getValidations() {
		return createTypedQuery("select v from ValidationRule v")
				.getResultList();
	}

}
