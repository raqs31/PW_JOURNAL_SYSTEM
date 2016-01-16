package pw.mario.common.action;

import pw.mario.common.exception.PerformActionException;

public interface Action {
	public void doAction() throws PerformActionException;
}
