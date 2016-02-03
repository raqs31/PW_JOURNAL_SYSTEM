package pw.mario.common.exception;

import lombok.Getter;

public class LockException extends PerformActionException {
	private static final long serialVersionUID = 1L;
	@Getter private Object lockedObject;
	
	public LockException(String message, Object lockedObject) {
		super(message);
		this.lockedObject = lockedObject;
	}
}
