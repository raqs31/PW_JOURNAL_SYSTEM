package pw.mario.common.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PerformActionException extends Exception {
	private static final long serialVersionUID = 1L; 
	
	public PerformActionException(String message) {
		super(message);
	}
	
}
