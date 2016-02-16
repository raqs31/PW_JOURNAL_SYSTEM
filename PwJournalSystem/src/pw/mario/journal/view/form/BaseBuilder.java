package pw.mario.journal.view.form;

import javax.faces.component.UIComponent;

import pw.mario.common.api.ComponentBuilder;

public abstract class BaseBuilder<T> implements ComponentBuilder {
	protected T element;
	protected String path;
	protected boolean editable;
	private UIComponent root;
	
	public BaseBuilder(T element, String path, boolean editable) {
		this.element = element;
		this.path = path;
		this.editable = editable;
	}
	
	public BaseBuilder(T element, String path) {
		this.element = element;
		this.path = path;
		this.editable = true;
	}
	
	protected void put(UIComponent component) {
		root.getChildren().add(component);
	}
	
	protected void put(UIComponent root, UIComponent toAdd) {
		root.getChildren().add(toAdd);
	}
	
	protected void setRoot(UIComponent root) {
		this.root = root;
	}
	
	protected UIComponent getRoot() {
		return root;
	}
	
	@Override
	public ComponentBuilder setEditable(boolean isEditable) {
		this.editable = isEditable;
		return this;
	}
}
