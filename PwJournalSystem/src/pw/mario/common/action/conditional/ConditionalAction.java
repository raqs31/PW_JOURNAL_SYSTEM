package pw.mario.common.action.conditional;

import pw.mario.common.action.Action;

public interface ConditionalAction extends Action {
	boolean allowed(ConditionValidator validator);
}
