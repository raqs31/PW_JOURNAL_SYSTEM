package pw.mario.faces.api.action;

public interface OnConfirmAction extends Action {
	
	String getHeader();
	
	String getMessage();
	
	String getIcon();
	
	String buttonValue();
}
