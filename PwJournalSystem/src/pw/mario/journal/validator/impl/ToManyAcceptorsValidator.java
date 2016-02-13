package pw.mario.journal.validator.impl;

import javax.enterprise.context.ApplicationScoped;

import pw.mario.common.exception.ValidationError;
import pw.mario.journal.model.dictionary.ValidationRule;
import pw.mario.journal.qualifiers.Validator;
import pw.mario.journal.service.article.ExecutionContext;
import pw.mario.journal.validator.ArticleValidator;
import pw.mario.journal.validator.ArticleValidatorBuilder;

@Validator
@ApplicationScoped
public class ToManyAcceptorsValidator implements ArticleValidatorBuilder {
	
	private static class ToManyAcceptorsValidatorInstance extends AbstractValidator {
		
		protected ToManyAcceptorsValidatorInstance(ValidationRule validation) {
			super(validation);
		}

		@Override
		public boolean validate(ExecutionContext ctx) {
			if (ctx.getAcceptors() != null && ctx.getAcceptors().size() > validation.getNAttr2()) {
				ctx.addError(new ValidationError(ctx, validation));
				return false;
			}
			return true;
		}
	}

	@Override
	public ArticleValidator build(ValidationRule rule) {
		return new ToManyAcceptorsValidatorInstance(rule);
	}
	
	@Override
	public String name() {
		return "TOO_MANY_ACC";
	}
}
