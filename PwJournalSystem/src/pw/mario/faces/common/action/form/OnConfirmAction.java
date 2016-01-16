package pw.mario.faces.common.action.form;

import pw.mario.faces.common.action.Action;

public interface OnConfirmAction extends Action {
	
	String getHeader();
	
	String getMessage();
	
	String getIcon();
	
	String buttonValue();
	
	String afterAction();
}
