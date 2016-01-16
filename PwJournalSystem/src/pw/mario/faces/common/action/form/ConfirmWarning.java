package pw.mario.faces.common.action.form;



import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pw.mario.faces.common.action.Action;
import pw.mario.faces.common.exception.PerformActionException;

@Builder
public class ConfirmWarning implements OnConfirmAction {
	@Setter private Action action;
	@Getter @Setter private String message;
	@Setter private String buttonValue;
	private boolean actionSucces = false;
	@Setter @Getter private String toRedirect;
	
//	public ConfirmWarning(String toRedirect) {
//		this.toRedirect = toRedirect;
//	}
	
	@Override
	public void doAction() {
		if (action == null)
			throw new IllegalStateException("Action cannot be null");
		try {
			action.doAction();
			actionSucces = true;
		} catch (PerformActionException e) {
			actionSucces = false;
		}
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

	@Override
	public String afterAction() {
		if (actionSucces)
			return getToRedirect();
		return null;
	}
}
