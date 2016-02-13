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
public class PickManagerValidator implements ArticleValidatorBuilder {

	@Override
	public ArticleValidator build(ValidationRule rule) {
		return new PickManagerValidatorInstance(rule);
	}
	@Override
	public String name() {
		return "PICK_MNG";
	}
	
	private static class PickManagerValidatorInstance extends AbstractValidator {
		protected PickManagerValidatorInstance(ValidationRule validation) {
			super(validation);
		}

		@Override
		public boolean validate(ExecutionContext ctx) {
			if (ctx.getManager() == null) {
				ctx.addError(new ValidationError(ctx, validation));
				return false;
			}
			return true;
		}
	}

}
