package pw.mario.faces.common.action;

import pw.mario.faces.common.api.Action;

public interface OnConfirmAction extends Action {
	
	String getHeader();
	
	String getMessage();
	
	String getIcon();
	
	String buttonValue();
	
	String afterAction();
}
