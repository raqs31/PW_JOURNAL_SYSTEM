package pw.mario.journal.validator;


import pw.mario.journal.service.article.ExecutionContext;

public interface ArticleValidator {
	boolean validate(ExecutionContext ctx);
}
