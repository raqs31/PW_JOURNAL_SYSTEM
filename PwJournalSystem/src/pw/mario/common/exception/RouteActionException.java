package pw.mario.common.exception;

import lombok.Getter;

public class RouteActionException extends PerformActionException {
	private static final long serialVersionUID = 1L;
	@Getter private String details;
	
	public RouteActionException() {};
	public RouteActionException(String message) {
		super(message);
	}
	
	public RouteActionException(String message, String details) {
		super(message);
		this.details = details;
	}
}
