package pw.mario.faces.common.action.conditional;

import pw.mario.faces.common.action.Action;

public interface ConditionalAction extends Action {
	boolean allowed(ConditionValidator validator);
}
