package pw.mario.journal.dao;

import java.util.Collection;
import java.util.List;

import pw.mario.journal.model.article.Rule;
import pw.mario.journal.model.dictionary.ValidationRule;

public interface RulesDao {
	Rule getRule(Long ruleId);
	
	List<Rule> getRules();
	
	Collection<ValidationRule> getRuleValidations(Rule rule);
	
	Collection<ValidationRule> getRuleValidations(Long ruleId);
}
