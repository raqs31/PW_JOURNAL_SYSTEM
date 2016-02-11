package pw.mario.journal.validator;


import pw.mario.journal.data.ExecutionContext;

public interface ArticleValidator {
	boolean validate(ExecutionContext ctx);
}
