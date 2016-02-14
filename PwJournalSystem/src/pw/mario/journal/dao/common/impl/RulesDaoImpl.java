package pw.mario.journal.dao.common.impl;

import java.util.Collection;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;

import pw.mario.journal.dao.AbstractDAOImpl;
import pw.mario.journal.dao.common.RulesDao;
import pw.mario.journal.model.article.Rule;
import pw.mario.journal.model.dictionary.ValidationRule;

@Default
@Dependent
public class RulesDaoImpl extends AbstractDAOImpl<Rule> implements RulesDao {

	@Override
	public Rule getRule(Long ruleId) {
		return find(ruleId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rule> getRules() {
		return em.createQuery("select r from Rule").getResultList();
	}

	@Override
	public Collection<ValidationRule> getRuleValidations(Rule rule) {
		return getRuleValidations(rule.getRuleId());
	}

	@Override
	public Collection<ValidationRule> getRuleValidations(Long ruleId) {
		return em.createQuery("select v from Rule r join r.validations v where r.ruleId = ?1", ValidationRule.class)
				.setParameter(1, ruleId)
				.getResultList();
	}
}
