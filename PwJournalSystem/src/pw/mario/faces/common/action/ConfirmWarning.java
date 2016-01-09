package pw.mario.faces.common.action;


import lombok.Getter;
import lombok.Setter;
import pw.mario.faces.common.api.Action;

public class ConfirmWarning implements OnConfirmAction {
	@Setter private Action action;
	@Getter @Setter private String message;
	@Setter private String buttonValue;
	
	@Override
	public void doAction() {
		if (action == null)
			throw new IllegalStateException("Action cannot be null");
		action.doAction();
	}
	
	@Override
	public String getHeader() {
		return "Potwierdzenie";
	}

	@Override
	public String getIcon() {
		return "ui-icon-alert";
	}

	@Override
	public String buttonValue() {
		return buttonValue;
	}
}
