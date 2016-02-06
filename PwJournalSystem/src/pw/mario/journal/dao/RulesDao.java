package pw.mario.journal.dao;

import java.util.List;

import pw.mario.journal.model.Rule;

public interface RulesDao {
	Rule getRule(Long ruleId);
	
	List<Rule> getRules();
}
