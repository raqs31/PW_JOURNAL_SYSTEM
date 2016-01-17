package pw.mario.common.action.form;

import java.io.Serializable;

import pw.mario.common.action.conditional.ConditionalAction;

public interface ButtonAction<T> extends ConditionalAction<T>, Serializable {
	String getAction();
	String getValue();
	String getId();
}
