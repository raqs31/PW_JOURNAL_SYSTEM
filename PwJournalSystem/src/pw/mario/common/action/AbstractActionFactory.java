package pw.mario.common.action;

import java.util.Collection;

import pw.mario.journal.model.User;

public abstract class AbstractActionFactory<T extends Action, V> {
	public abstract Collection<T> getActions(V a, User u);
}