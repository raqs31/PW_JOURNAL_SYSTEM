package pw.mario.common.handler;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.apache.log4j.Logger;

public class JournalExceptionHandler  extends ExceptionHandlerWrapper {
	 private static final Logger log = Logger.getLogger(JournalExceptionHandler.class.getCanonicalName());
	    private ExceptionHandler wrapped;
	 
	    JournalExceptionHandler(ExceptionHandler exception) {
	        this.wrapped = exception;
	    }
	 
	    @Override
	    public ExceptionHandler getWrapped() {
	        return wrapped;
	    }
	 
	    @Override
	    public void handle() throws FacesException {
	 
	        final Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
	        while (i.hasNext()) {
	            ExceptionQueuedEvent event = i.next();
	            ExceptionQueuedEventContext context =
	                    (ExceptionQueuedEventContext) event.getSource();
	 
	            // get the exception from context
	            Throwable t = context.getException();
	 
	            final FacesContext fc = FacesContext.getCurrentInstance();
	            final Map<String, Object> requestMap = fc.getExternalContext().getRequestMap();
	            final NavigationHandler nav = fc.getApplication().getNavigationHandler();
	 
	            //here you do what ever you want with exception
	            try {
	 
	            	log.error("Exception", t);

	            	requestMap.put("exceptionMessage", t.getMessage());
	                nav.handleNavigation(fc, null, "/errors/general-error");
	                fc.renderResponse();
	 
	            } finally {
	                //remove it from queue
	                i.remove();
	            }
	        }
	        //parent hanle
	        getWrapped().handle();
	    }
}
