package pw.mario.journal.validator;

import pw.mario.journal.model.dictionary.ValidationRule;

public interface ArticleValidatorBuilder {
	ArticleValidator build(ValidationRule rule);
	
	String name();
}
