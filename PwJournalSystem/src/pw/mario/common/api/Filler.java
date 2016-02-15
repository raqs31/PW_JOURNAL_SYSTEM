package pw.mario.common.api;

public interface Filler <T> {
	void fill(T toFill);
	
	void propagate(T toPropagate);
}
