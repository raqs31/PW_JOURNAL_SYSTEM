package pw.mario.journal.validator;

import pw.mario.journal.model.dictionaries.ValidationRule;

public interface ArticleValidatorBuilder {
	ArticleValidator build(ValidationRule rule);
	
	String name();
}
