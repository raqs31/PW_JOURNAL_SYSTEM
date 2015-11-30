package pw.mario.faces.handler;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class JournalExceptionHandlerFactory extends ExceptionHandlerFactory {
	private ExceptionHandlerFactory parent;

	public JournalExceptionHandlerFactory(ExceptionHandlerFactory parent) {
	    this.parent = parent;
	   }

	@Override
	public ExceptionHandler getExceptionHandler() {
		ExceptionHandler handler = new JournalExceptionHandler(parent.getExceptionHandler());

		return handler;
	}
}
