package pw.mario.faces.common.action;

public interface OnConfirmAction extends Action {
	
	String getHeader();
	
	String getMessage();
	
	String getIcon();
	
	String buttonValue();
	
	String afterAction();
}
