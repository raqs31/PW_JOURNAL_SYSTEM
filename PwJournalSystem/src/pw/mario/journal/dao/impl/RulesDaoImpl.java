package pw.mario.journal.dao.impl;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;

import pw.mario.journal.model.Rule;

import pw.mario.journal.dao.AbstractDAOImpl;
import pw.mario.journal.dao.RulesDao;

@Default
@Dependent
public class RulesDaoImpl extends AbstractDAOImpl<Rule> implements RulesDao {

}
