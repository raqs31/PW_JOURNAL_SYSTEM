package pw.mario.faces.common.api;

import pw.mario.faces.common.exception.PerformActionException;

public interface Action {
	public void doAction() throws PerformActionException;
}
