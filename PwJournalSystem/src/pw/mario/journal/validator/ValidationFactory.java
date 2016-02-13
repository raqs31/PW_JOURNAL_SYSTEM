package pw.mario.journal.validator;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import pw.mario.journal.dao.dictionary.ValidationRulesDao;
import pw.mario.journal.model.article.Rule;
import pw.mario.journal.model.dictionary.ValidationRule;
import pw.mario.journal.qualifiers.Validator;

@ApplicationScoped
public class ValidationFactory {
	@Inject private ValidationRulesDao validationDao;
	@Inject @Validator private Map<String, ArticleValidatorBuilder> validationMap;
	
	public List<ArticleValidator> getValidations(Rule r) {
		List<ArticleValidator> toReturn = new LinkedList<>();
		List<ValidationRule> validationRules = validationDao.getValidations(r);
		
		if (validationRules.isEmpty())
			return toReturn;
		
		for (ValidationRule rule: validationRules) {
			if (!validationMap.containsKey(rule.getCode()))
				throw new RuntimeException("Walidacja " + rule.getCode() + " nie jest zaimplementowana");
			toReturn.add(validationMap.get(rule.getCode()).build(rule));
		}
		
		return toReturn;
	}
	
}
