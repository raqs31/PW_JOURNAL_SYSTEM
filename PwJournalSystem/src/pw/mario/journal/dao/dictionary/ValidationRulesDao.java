package pw.mario.journal.dao.dictionary;

import java.util.List;

import pw.mario.journal.model.Rule;
import pw.mario.journal.model.dictionaries.ValidationRule;

public interface ValidationRulesDao {
	List<ValidationRule> getValidations(Rule rule);
	
	List<ValidationRule> getValidations();
}
