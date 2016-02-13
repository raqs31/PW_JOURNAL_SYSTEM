package pw.mario.journal.validator.impl;

import pw.mario.journal.model.dictionary.ValidationRule;
import pw.mario.journal.validator.ArticleValidator;

public abstract class AbstractValidator implements ArticleValidator {
	protected ValidationRule validation;

	protected AbstractValidator(ValidationRule validation) {
		this.validation = validation;
	}
}
