package pw.mario.journal.dao.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;

import pw.mario.journal.dao.AbstractDAOImpl;
import pw.mario.journal.dao.RulesDao;
import pw.mario.journal.model.Rule;

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
}
