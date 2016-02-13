package pw.mario.common.action;

import java.util.Collection;

import pw.mario.common.api.Refreshable;
import pw.mario.journal.model.common.User;

public abstract class AbstractActionFactory<T extends Action, V> {
	public abstract Collection<T> getActions(V a, User u, Refreshable toRefresh);
}
