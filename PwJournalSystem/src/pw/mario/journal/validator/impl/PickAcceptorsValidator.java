package pw.mario.journal.validator.impl;

import javax.enterprise.context.ApplicationScoped;

import pw.mario.common.exception.ValidationError;
import pw.mario.journal.data.ExecutionContext;
import pw.mario.journal.model.dictionaries.ValidationRule;
import pw.mario.journal.qualifiers.Validator;
import pw.mario.journal.validator.ArticleValidator;
import pw.mario.journal.validator.ArticleValidatorBuilder;

@Validator
@ApplicationScoped
public class PickAcceptorsValidator implements ArticleValidatorBuilder  {
	
	@Override
	public ArticleValidator build(ValidationRule rule) {
		return new PickAcceptorsValidatorInstance(rule);
	}
	
	@Override
	public String name() {
		return "PICK_ACC";
	}

	private static class PickAcceptorsValidatorInstance extends AbstractValidator  {
		
		protected PickAcceptorsValidatorInstance(ValidationRule validation) {
			super(validation);
		}

		@Override
		public boolean validate(ExecutionContext ctx) {
			if (ctx.getAcceptors() == null || ctx.getAcceptors().isEmpty()) {
				ctx.addError(new ValidationError(ctx, validation));
				return false;
			}
			return true;
		}
	}

}
