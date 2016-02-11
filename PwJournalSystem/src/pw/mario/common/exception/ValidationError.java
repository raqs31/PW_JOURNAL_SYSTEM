package pw.mario.common.exception;

import javax.faces.application.FacesMessage;

import pw.mario.journal.data.ExecutionContext;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.Rule;
import pw.mario.journal.model.dictionaries.ValidationRule;

public class ValidationError {
	private final Article article;
	private final Rule rule;
	private final ValidationRule validation;

	public ValidationError(final Article article, final Rule rule, final ValidationRule validation) {
		this.article = article;
		this.rule = rule;
		this.validation = validation;
	}
	
	public ValidationError(final ExecutionContext ctx, final ValidationRule validation) {
		this(ctx.getArticle(), ctx.getRule(), validation);
	}
	
	public FacesMessage convert() {
		return new FacesMessage(validation.getSeverity(), "Walidacja " + validation.getCode(), validation.getDesciption());
	}
}
