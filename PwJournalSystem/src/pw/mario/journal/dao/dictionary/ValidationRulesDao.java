package pw.mario.journal.dao.dictionary;

import java.util.List;

import pw.mario.journal.model.article.Rule;
import pw.mario.journal.model.dictionary.ValidationRule;

public interface ValidationRulesDao {
	List<ValidationRule> getValidations(Rule rule);
	
	List<ValidationRule> getValidations();
}
