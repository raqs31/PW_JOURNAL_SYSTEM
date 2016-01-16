package pw.mario.common.action.form;

import pw.mario.common.action.Action;

public interface OnConfirmAction extends Action {
	
	String getHeader();
	
	String getMessage();
	
	String getIcon();
	
	String buttonValue();
	
	String afterAction();
}
