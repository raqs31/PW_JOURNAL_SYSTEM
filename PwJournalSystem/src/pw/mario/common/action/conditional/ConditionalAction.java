package pw.mario.common.action.conditional;

import pw.mario.common.action.Action;

public interface ConditionalAction<T> extends Action {
	boolean allowed(ConditionValidator<T> validator);
}
