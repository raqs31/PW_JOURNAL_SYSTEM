package pw.mario.common.action;

public interface ConditionalAction extends Action {
	boolean allowed();
}
