package pw.mario.faces.common.action;

import pw.mario.faces.common.exception.PerformActionException;

public interface Action {
	public void doAction() throws PerformActionException;
}
